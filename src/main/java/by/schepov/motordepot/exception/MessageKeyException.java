package by.schepov.motordepot.exception;

import by.schepov.motordepot.parameter.MessageKey;

public class MessageKeyException extends Exception{

    protected boolean hasKey = false;
    protected MessageKey messageBundleKey;

    public MessageKeyException() {
    }

    public MessageKeyException(String message) {
        super(message);
    }

    public MessageKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageKeyException(Throwable cause) {
        super(cause);
    }

    public void setMessageBundleKey(MessageKey messageBundleKey) {
        hasKey = messageBundleKey != null;
        this.messageBundleKey = messageBundleKey;
    }

    public MessageKey getMessageBundleKey() {
        return messageBundleKey;
    }

    public boolean hasMessageBundleKey() {
        return hasKey;
    }

}
