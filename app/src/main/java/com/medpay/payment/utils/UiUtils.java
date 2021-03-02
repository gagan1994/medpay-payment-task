package com.medpay.payment.utils;

import android.app.Activity;
import android.view.View;

import com.medpay.payment.R;
import com.wooplr.spotlight.SpotlightView;

public class UiUtils {
    public static void showOnboardingScreen(Activity activity, View view) {
        new SpotlightView.Builder(activity)
                .enableDismissAfterShown(false)
                .introAnimationDuration(400)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(R.color.purple_700)
                .headingTvSize(32)
                .headingTvText(activity.getString(R.string.on_board_heading))
                .subHeadingTvColor(R.color.white)
                .subHeadingTvSize(16)
                .subHeadingTvText(activity.getString(R.string.on_board_contents))
                .target(view)
                .lineAnimDuration(400)
                .maskColor(R.color.failure)
                .lineAndArcColor(R.color.white)
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .show();
    }
}
