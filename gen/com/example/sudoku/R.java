/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.example.sudoku;

public final class R {
    public static final class array {
        public static final int names=0x7f070000;
    }
    public static final class attr {
        /** <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>left</code></td><td>0</td><td></td></tr>
<tr><td><code>right</code></td><td>1</td><td></td></tr>
</table>
         */
        public static final int labelPosition=0x7f010001;
        /** <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static final int showText=0x7f010000;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f050000;
        public static final int activity_vertical_margin=0x7f050001;
    }
    public static final class drawable {
        public static final int background=0x7f020000;
        public static final int bg=0x7f020001;
        public static final int btn1=0x7f020002;
        public static final int btn1p=0x7f020003;
        public static final int btn2=0x7f020004;
        public static final int btn2p=0x7f020005;
        public static final int btnx=0x7f020006;
        public static final int btnx2=0x7f020007;
        public static final int ic_launcher=0x7f020008;
    }
    public static final class id {
        public static final int about=0x7f040005;
        public static final int action_settings=0x7f040006;
        public static final int difficulty=0x7f040004;
        public static final int imageButton=0x7f040002;
        public static final int left=0x7f040000;
        public static final int listView1=0x7f040003;
        public static final int right=0x7f040001;
    }
    public static final class layout {
        public static final int activity_grid_sudoku=0x7f030000;
        public static final int activity_suduku_main=0x7f030001;
    }
    public static final class menu {
        public static final int menu_top_home=0x7f090000;
        public static final int suduku_main=0x7f090001;
    }
    public static final class string {
        public static final int action_settings=0x7f060001;
        public static final int app_name=0x7f060000;
        public static final int title=0x7f060002;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f080000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f080001;
    }
    public static final class styleable {
        /** Attributes that can be used with a Grid.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #Grid_labelPosition com.example.sudoku:labelPosition}</code></td><td></td></tr>
           <tr><td><code>{@link #Grid_showText com.example.sudoku:showText}</code></td><td></td></tr>
           </table>
           @see #Grid_labelPosition
           @see #Grid_showText
         */
        public static final int[] Grid = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link com.example.sudoku.R.attr#labelPosition}
          attribute's value can be found in the {@link #Grid} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>left</code></td><td>0</td><td></td></tr>
<tr><td><code>right</code></td><td>1</td><td></td></tr>
</table>
          @attr name com.example.sudoku:labelPosition
        */
        public static final int Grid_labelPosition = 1;
        /**
          <p>This symbol is the offset where the {@link com.example.sudoku.R.attr#showText}
          attribute's value can be found in the {@link #Grid} array.


          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name com.example.sudoku:showText
        */
        public static final int Grid_showText = 0;
    };
}