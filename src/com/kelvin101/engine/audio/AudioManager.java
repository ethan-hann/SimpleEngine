package com.kelvin101.engine.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

//TODO: fix bug with not seeking audio properly!
/**
 * Class to handle all loading and playing of songs or audio clips.
 * <p>
 *     Clips are added to the <code>clips</code> hashMap and are accessed using
 *     a unique name. Multiple audio clips can be played at the same time because of the
 *     use of different HashMaps for clips, streams, statuses, positions, paths, looping, and volumes.
 * </p>
 * <p>
 *     The available formats for audio clips are the same as those in the built-in Java {@link AudioFileFormat} class.
 *     These include:
 *     <ul>
 *         <li>WAVE - .wav</li>
 *         <li>AU - .au</li>
 *         <li>AIFF - .aif</li>
 *         <li>AIFF-C - .aifc</li>
 *         <li>SND - .snd</li>
 *     </ul>
 * </p>
 *
 */
public class AudioManager
{
    private static AudioManager instance;

    private HashMap<String, Clip> clips;
    private HashMap<String, AudioInputStream> streams;
    private HashMap<String, String> clipStatus;
    private HashMap<String, Long> clipPositions;
    private HashMap<String, String> clipPaths;
    private HashMap<String, Boolean> shouldClipLoop;
    private HashMap<String, FloatControl> gainControls;

    private AudioManager()
    {
        clips = new HashMap<>();
        streams = new HashMap<>();
        clipStatus = new HashMap<>();
        clipPositions = new HashMap<>();
        clipPaths = new HashMap<>();
        shouldClipLoop = new HashMap<>();
        gainControls = new HashMap<>();
    }

    public static AudioManager getInstance()
    {
        if (instance == null)
        {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Adds an audio clip to the <code>clips</code> hashMap to be manipulated.
     * @param audioPath : the file path to the audio clip
     * @param clipName : a unique clip name for referencing the hashMap
     * @param shouldLoop : should the clip loop?
     * @return true if clip was added successfully; false if not
     */
    public boolean addAudioClip(String audioPath, String clipName, boolean shouldLoop)
    {
        try
        {
            streams.put(clipName, AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile()));
            clips.put(clipName, AudioSystem.getClip());
            clipPaths.put(clipName, audioPath);
            shouldClipLoop.put(clipName, shouldLoop);

            clips.get(clipName).open(streams.get(clipName));
            gainControls.put(clipName, (FloatControl) clips.get(clipName).getControl(FloatControl.Type.MASTER_GAIN));
            return true;
        } catch (UnsupportedAudioFileException |
                LineUnavailableException |
                IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes an audio clip from the hashMap.
     * @param clipName : a unique clip name for referencing the hashMap
     * @return true if the clip was successfully removed; false if not
     */
    public boolean removeAudioClip(String clipName)
    {
        if (!clips.containsKey(clipName))
        {
            return false;
        }
        clips.get(clipName).close();
        clips.remove(clipName);
        return true;
    }

    /**
     * Plays an audio clip from the hashMap.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    public void playClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            if (shouldClipLoop.get(clipName))
            {
                clips.get(clipName).loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {
                clips.get(clipName).loop(0);
            }
            clipStatus.put(clipName, "play");
        }
    }

    /**
     * Pauses an audio clip from the hashMap.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    public void pauseClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            if (!clipStatus.get(clipName).equals("paused"))
            {
                clipPositions.put(clipName, clips.get(clipName).getMicrosecondPosition());
                clips.get(clipName).stop();
                clipStatus.put(clipName, "paused");
            }
        }
    }

    /**
     * Resumes an audio clip from the last position.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    public void resumeClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            if (!clipStatus.get(clipName).equals("play"))
            {
                clips.get(clipName).close();
                resetAudioStream(clipName);
                clips.get(clipName).setMicrosecondPosition(clipPositions.get(clipName));
                playClip(clipName);
            }
        }
    }

    /**
     * Restarts the clip from the beginning.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    public void restartClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            clips.get(clipName).stop();
            clips.get(clipName).close();

            resetAudioStream(clipName);
            clipPositions.put(clipName, 0L);
            clips.get(clipName).setMicrosecondPosition(clipPositions.get(clipName));

            playClip(clipName);
        }
    }

    /**
     * Stops an audio clip and resets its position.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    public void stopClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            clipPositions.put(clipName, 0L);
            clips.get(clipName).stop();
            clips.get(clipName).close();
        }
    }

    /**
     * Seeks (or jumps) ahead in the audio clip by the specified amount of milliseconds.
     * @param clipName : a unique clip name for referencing the hashMap
     * @param ms : the amount of milliseconds to jump ahead
     */
    public void seek(String clipName, long ms)
    {
        if (clips.containsKey(clipName))
        {
            if (ms > 0 && ms < clips.get(clipName).getMicrosecondLength())
            {
                clipPositions.put(clipName, ms);
                clips.get(clipName).stop();
                clips.get(clipName).close();
                resetAudioStream(clipName);
                clips.get(clipName).setMicrosecondPosition(clipPositions.get(clipName));
                playClip(clipName);
            }
        }
    }

    /**
     * Resets the audio stream associated with a clip. This is an internal class method.
     * @param clipName : a unique clip name for referencing the hashMap
     */
    private void resetAudioStream(String clipName)
    {
        String clipPath = clipPaths.get(clipName);
        try
        {
            streams.put(clipName, AudioSystem.getAudioInputStream(new File(clipPath).getAbsoluteFile()));
            clips.get(clipName).open(streams.get(clipName));
            if (shouldClipLoop.get(clipName))
            {
                clips.get(clipName).loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (UnsupportedAudioFileException |
                LineUnavailableException |
                IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Increase or decrease the gain amount for the specified clip. This controls the volume of the
     *  audio clip.
     * @param clipName : a unique clip name for referencing the hashMap
     * @param gainAmt : a float representing the gain amount; positive gain indicates increase in volume.
     *                Negative gain is a decrease in volume.
     */
    public void setGain(String clipName, float gainAmt)
    {
        if (clips.containsKey(clipName))
        {
            FloatControl newGain = (FloatControl) clips.get(clipName).getControl(FloatControl.Type.MASTER_GAIN);
            newGain.setValue(gainAmt);
            gainControls.put(clipName, newGain);
        }
    }

    /**
     * Sets the volume of the specified audio clip.
     * @param clipName : a unique clip name for referencing the hashMap
     * @param volume : a double representing the loudness of the audio. 0.0 is silent -> 1.0 is max volume
     */
    public void setVolume(String clipName, double volume)
    {
        if (clips.containsKey(clipName))
        {
            FloatControl newVolume = (FloatControl) clips.get(clipName).getControl(FloatControl.Type.MASTER_GAIN);
            float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            newVolume.setValue(db);
            gainControls.put(clipName, newVolume);
        }
    }

    /**
     * Get the specified clip.
     * @param clipName : a unique clip name for referencing the hashMap
     * @return the Clip object that corresponds to the <code>clipName</code>; null if
     * the clip does not exist
     */
    public Clip getClip(String clipName)
    {
        if (clips.containsKey(clipName))
        {
            return clips.get(clipName);
        }
        return null;
    }

    /**
     * Get all audio clips.
     * @return clips : a hashMap containing all audio clips that have been added
     */
    public HashMap<String, Clip> getClips()
    {
        return clips;
    }
}
