package com.example.developermode.blackhole.GameFramework;

/**
 * Created by DeveloperMode on 2017-05-21.
 */

public class Timer {
    long m_lCurrentTime;
    long m_lLastTime;
    float m_fElapsedTime;

    public Timer() {
        m_lCurrentTime = System.currentTimeMillis();
        m_lLastTime = m_lCurrentTime;
        m_fElapsedTime = 0.0f;
    }

    public void Tick() {
        m_lCurrentTime = System.currentTimeMillis();
        m_fElapsedTime = (m_lCurrentTime - m_lLastTime);
        m_fElapsedTime = m_fElapsedTime / 1000.0f;
        m_lLastTime = m_lCurrentTime;
    }

    public float GetElapsedTime() {
        return m_fElapsedTime;
    }
}
