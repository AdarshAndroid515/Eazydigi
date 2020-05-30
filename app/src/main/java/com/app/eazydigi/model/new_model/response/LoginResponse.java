package com.app.eazydigi.model.new_model.response;

public class LoginResponse {

    /**
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzY2hvb2xOdW0iOm51bGwsInN1YiI6InNzbmFsYW5kYSIsImxvZ2luVXNlciI6eyJ1c2VySWQiOjU5NSwic2Nob29sSWQiOjUsImVtYWlsSWQiOiJhbmFuZHZhcmRoYW5zaW5oYUBnbWFpbC5jb20iLCJ1c2VyTmFtZSI6InNzbmFsYW5kYSIsInBob25lIjpudWxsLCJmaXJzdE5hbWUiOiJTYW1wbGUgU2Nob29sLCBOYWxhbmRhIiwibWlkZGxlTmFtZSI6bnVsbCwibGFzdE5hbWUiOiJhZG1pbiIsImRvYiI6bnVsbCwiaXNBY2NvdW50RXhwaXJlZCI6bnVsbCwiZXhwaXJ5RGF0ZSI6bnVsbCwiaXNFbmFibGVkIjowLCJwYXNzUmVzZXRUb2tlbiI6bnVsbCwicGFzc1Jlc2V0VG9rZW5FeHBpcnkiOm51bGwsInJvbGVzIjpbeyJyb2xlSWQiOjIsIm5hbWUiOiJBRE1JTiIsImRlc2NyaXB0aW9uIjoic2Nob29sIGFkbWluIHJvbGUifV19LCJzY2hvb2xBZGRlc3MiOiJOYWxhbmRhIENhbnR0LCBNYWluIFJvYWQsIE5hbGFuZGEiLCJzY2hvb2xFbWFpbCI6ImFuYW5kdmFyZGhhbnNpbmhhQGdtYWlsLmNvbSIsInNjaG9vbEFmZmxOdW0iOiI5ODk4OTgxMjE4OTg5OCIsInNjaG9vbE5hbWUiOiJTYW1wbGUgU2Nob29sLCBOYWxhbmRhIiwiZXhwIjoxNTkwNTg2Njg2LCJ1c2VyRGV0YWlscyI6eyJwYXNzd29yZCI6IiQyYSQxMCRTa1BkSFU3NUdleHJNcnpkLzNObTBPYXZLbXFJTTJEMWxsNnNJNEgyS0wwWFVWbmtiUVZQaSIsInVzZXJuYW1lIjoic3NuYWxhbmRhIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImFjY291bnROb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWUsImVuYWJsZWQiOnRydWV9LCJzY2hvb2xMb2dvIjoic2Nob29sLTUuanBlZyIsImlhdCI6MTU4OTk4MTg4Niwic2Nob29sTW9iTm8iOjc3MjAwMDcwNDh9.yXMf2bN88yzAn5FZbInzcn6NzXExRIo-ok49Gf6CjTwFf67No8PlxV5BEDZ5QwWz2VU8MOIt9PWMDBm5ek6p1w
     */

    private String token;
    /**
     * status : 0
     * message : INVALID_CREDENTIALS
     * data : null
     */

    private int status;
    private String message;
    private Object data;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
