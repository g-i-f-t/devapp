/**
 * Author. Aerain
 * SSLAB
 * Jeju National University
 */

package com.example.activity_maim;

import android.app.Application;

import com.example.activity_maim.VO.Profile;
import com.example.activity_maim.splash.ProfileManager;

public class GiftApplication extends Application {
    private ProfileManager profileManager;
    @Override
    public void onCreate() {
        super.onCreate();
        profileManager = new ProfileManager();
    }

    private Profile profile = null;
    public void setUserInfo(Profile profile) {
        this.profile = profile;
    }
    public Profile userInfo() {
        return profile;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }
}
