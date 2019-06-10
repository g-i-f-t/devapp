package com.example.activity_maim.splash;

import android.os.AsyncTask;

import com.example.activity_maim.Room.UserDao;
import com.example.activity_maim.User;

import java.util.HashMap;
import java.util.Map;

public class RoomLog {
    public static class addDBTask extends AsyncTask<User, Void, Void> {
        private final UserDao roomUserDao;

        public addDBTask(UserDao roomUserDao) {
            this.roomUserDao = roomUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            for(User user: users)
                roomUserDao.add(user);
            final String result = roomUserDao.getAll().get(0).getUserSeqNo();

            return null;
        }
    }

    public static class getDBTask extends AsyncTask<User, Void, Map<String, String>> {
        private final UserDao roomUserDao;

        public getDBTask(UserDao roomUserDao) {
            this.roomUserDao = roomUserDao;
        }

        @Override
        protected Map<String, String> doInBackground(User... users) {
            Map<String ,String > result = new HashMap<>();
            try {
                result.put("userSeqNo" , roomUserDao.getAll().get(0).getUserSeqNo());
                result.put("accessToken", roomUserDao.getAll().get(0).getAccessToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}