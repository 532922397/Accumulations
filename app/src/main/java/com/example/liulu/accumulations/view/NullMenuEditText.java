package com.example.liulu.accumulations.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by liulu on 2017/2/27
 */

public class NullMenuEditText extends EditText {

    boolean canPaste() {
        return false;
    }

    boolean canCut() {
        return false;
    }

    boolean canCopy() {
        return false;
    }

    boolean canSelectAllText() {
        return false;
    }

    boolean canSelectText() {
        return false;
    }

    boolean textCanBeSelected() {
        return false;
    }

    public NullMenuEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLongClickable(false);
        setTextIsSelectable(false);
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                Log.e("liulu", "onCreateActionMode");
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Log.e("liulu", "onPrepareActionMode");
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Log.e("liulu", "onActionItemClicked");
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.e("liulu", "onDestroyActionMode");
            }
        });
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        return true;
    }
}
