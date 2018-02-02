package in.fusionbit.shreejeemis.apimodels;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class SimpleResponse {
    /**
     * response : {"status":"1","message":"Task Completion added successfully"}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * status : 1
         * message : Task Completion added successfully
         */

        private String status;
        private String message;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
