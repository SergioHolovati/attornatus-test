package com.attornatus.test.infrastructure.exception;


public class HandlerExceptionDetails {
    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    private HandlerExceptionDetails(){

    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public static final class HandlerExceptionDetailsBuilder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private HandlerExceptionDetailsBuilder() {
        }

        public static HandlerExceptionDetailsBuilder aHandlerExceptionDetails() {
            return new HandlerExceptionDetailsBuilder();
        }

        public HandlerExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public HandlerExceptionDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public HandlerExceptionDetailsBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public HandlerExceptionDetailsBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public HandlerExceptionDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public HandlerExceptionDetails build() {
            HandlerExceptionDetails handlerExceptionDetails = new HandlerExceptionDetails();
            handlerExceptionDetails.detail = this.detail;
            handlerExceptionDetails.status = this.status;
            handlerExceptionDetails.developerMessage = this.developerMessage;
            handlerExceptionDetails.timestamp = this.timestamp;
            handlerExceptionDetails.title = this.title;
            return handlerExceptionDetails;
        }
    }
}
