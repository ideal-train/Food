package com.wevey.selector.dialog;

import android.view.View;

/**
 * Created by Weavey on 2016/12/4.
 */

public interface SelectDialogInterface {

    interface OnLeftAndRightClickListener<T> {

        void clickLeftButton(T dialog, View view);

        void clickRightButton(T dialog, View view);

        void clickColseButton(T dialog, View view);
    }

    interface OnSingleClickListener<T> {

        void clickJustOneleButton(T dialog, View view);
    }

    interface OnItemClickListener<T> {

        void onItemClick(T dialog, View button, int position);
    }
}
