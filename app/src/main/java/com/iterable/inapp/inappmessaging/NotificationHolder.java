package com.iterable.inapp.inappmessaging;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by dtruong on 12/5/15.
 */
public class NotificationHolder extends PopupWindow {

    public ViewGroup mContainerView;

    /**
     * <p>Create a new non focusable popup window which can display the
     * <tt>contentView</tt>. The dimension of the window must be passed to
     * this constructor.</p>
     *
     * <p>The popup does not provide any background. This should be handled
     * by the content view.</p>
     *
     * @param contentView the popup's content
     * @param width the popup's width
     * @param height the popup's height
     */
    public NotificationHolder(View contentView, int width, int height) {
        super(contentView, width, height, false);
    }

/*    public void addItem()
    {
//        findViewById(R.id.fullscreen_content_controls)
//        R.layout


        
        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) inflater.inflate(
                R.layout.list_item_example, mContainerView, false);

        // Set the text in the new row to a random country.
                ((TextView) newView.findViewById(android.R.id.text1)).setText(
                COUNTRIES[(int) (Math.random() * COUNTRIES.length)]);

        // Set a click listener for the "X" button in the row that will remove the row.
        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the row from its parent (the container view).
                // Because mContainerView has android:animateLayoutChanges set to true,
                // this removal is automatically animated.
                mContainerView.removeView(newView);

                // If there are no rows remaining, show the empty view.
//                if (mContainerView.getChildCount() == 0) {
//                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
//                }
            }
        });


        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.
        //mContainerView.addView(newView, 0);
    }
*/
    /**
     * A static list of country names.
     */
    public static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain",
            "Austria", "Russia", "Poland", "Croatia", "Greece",
            "Ukraine",
    };
}
