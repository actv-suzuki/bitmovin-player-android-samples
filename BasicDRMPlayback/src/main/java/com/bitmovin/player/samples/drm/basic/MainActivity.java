/*
 * Bitmovin Player Android SDK
 * Copyright (C) 2017, Bitmovin GmbH, All Rights Reserved
 *
 * This source code and its use and distribution, is subject to the terms
 * and conditions of the applicable license agreement.
 */

package com.bitmovin.player.samples.drm.basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bitmovin.player.BitmovinPlayer;
import com.bitmovin.player.BitmovinPlayerView;
import com.bitmovin.player.config.drm.DRMSystems;
import com.bitmovin.player.config.media.SourceConfiguration;
import com.bitmovin.player.config.media.SourceItem;

import java.util.UUID;

public class MainActivity extends AppCompatActivity
{
    private BitmovinPlayerView bitmovinPlayerView;
    private BitmovinPlayer bitmovinPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bitmovinPlayerView = (BitmovinPlayerView) this.findViewById(R.id.bitmovinPlayerView);
        this.bitmovinPlayer = this.bitmovinPlayerView.getPlayer();

        this.initializePlayer();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.bitmovinPlayerView.onResume();
    }

    @Override
    protected void onPause()
    {
        this.bitmovinPlayerView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        this.bitmovinPlayerView.onDestroy();
        super.onDestroy();
    }

    protected void initializePlayer()
    {
        // Create a new source configuration
        SourceConfiguration sourceConfiguration = new SourceConfiguration();

        // Create a new source item
        SourceItem sourceItem = new SourceItem("https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd");

        // setup DRM handling
        String drmLicenseUrl = "http://widevine-proxy.appspot.com/proxy";
        UUID drmSchemeUuid = DRMSystems.WIDEVINE_UUID;
        sourceItem.addDRMConfiguration(drmSchemeUuid, drmLicenseUrl);

        // Add source item including DRM configuration to source configuration
        sourceConfiguration.addSourceItem(sourceItem);

        // load source using the created source configuration
        this.bitmovinPlayer.load(sourceConfiguration);
    }
}