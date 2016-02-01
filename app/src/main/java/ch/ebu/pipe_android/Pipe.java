package ch.ebu.pipe_android;

public class Pipe {
    // http://pipe-collect.ebu.io/v1/pipeconfig GET a appeler une fois au boot de l'appli si sur false = ne collecte pas dans un premier temps a voir pour une queue de calls

    // "endpoints" = conf a enrgister pour les prochains collect de data points

    // POST sur endpoint+/v1/collect  penser a setter le header origin android.rts.ch

    /**

    {
        "send_start": 0,
            "content_id": "string",
            "user_id": "string",
            "session_cookie_id": "string",
            "app_type": "string",
            "browser_data": {
        "javaenabled": true,
                "hostname": "string",
                "screencolors": "string",
                "language": "string",
                "referrer": "string",
                "charset": "string",
                "location": "string",
                "flashversion": "string",
                "pagetitle": "string",
                "viewportsize": "string",
                "timezone": 0,
                "page": "string",
                "screensize": "string"
    },
        "action_data": {
        "view_percentage": 0,
                "any_other_useful_user_data": "string",
                "view_status": "started"
    },
        "session_start": 0,
            "page_start": 0,
            "cookie_id": "string",
            "device_id": "string"
    }

     **/
}
