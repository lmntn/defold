package com.dynamo.cr.web2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class MessageNotification extends PopupPanel {

    private static final Binder binder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, MessageNotification> {
    }

    interface Style extends CssResource {
        String notification();
    }

    @UiField Style style;
    @UiField InlineLabel messageLabel;

    private int nextId = 0;

    public MessageNotification() {
        setWidget(binder.createAndBindUi(this));
        setStyleName(style.notification());
        setAnimationEnabled(false);
    }

    public void show(String message) {
        if (!isShowing()) {
            center();
            int left = (Window.getClientWidth() - getOffsetWidth()) >> 1;
            int top = 0;
            setPopupPosition(Math.max(Window.getScrollLeft() + left, 0), Math.max(
                Window.getScrollTop() + top, 0));

            show();
        }
        this.messageLabel.setText(message);

        Timer t = new Timer() {
            int id = nextId++;
            @Override
            public void run() {
                if (id == nextId - 1) {
                    hide();
                }
            }
        };

        t.schedule(3000);
    }

    public void setMessageAndShow(String message) {
    }

}