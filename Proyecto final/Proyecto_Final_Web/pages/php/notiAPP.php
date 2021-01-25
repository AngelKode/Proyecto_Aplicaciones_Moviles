<?php 
    $titulo = $_POST['TIT']; //ESTO ES FACIL PEPE, CAMBIALO POR LOS DATOS QUE MANDAS TÚ
    $descripcion = $_POST['DES'];
    $grupoid = $_POST['IDGRU'];
    // Cargamos los datos de la notificacion en un Array
    $notification = array();
    $notification['title'] = $titulo;
    $notification['body'] = $descripcion;      

    $topic = $grupoid;

    $fields = array(
        'to' => '/topics/' ."ID". $topic,
        'notification' => $notification
    );

    // Set POST variables
    $url = 'https://fcm.googleapis.com/fcm/send';

    $headers = array(
                'Authorization: key=AAAA7sXGyts:APA91bGgQhpueZvEgMwxbK3WJtCQIQVVumtUgM7Ne8Rtvdkh_CcKuT5qVqGawVjaeDCuNvw5EUNwTk7ifho2UU1oePJIRak6k5j7VdrqlhDKkItz29dOvzaMsSa3aczwtVwXAjCKdFJ0',
                'Content-Type: application/json'
                );
                
    // Open connection
    $ch = curl_init($url);
    // Set the url, number of POST vars, POST data
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    // Disabling SSL Certificate support temporarily
    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));       
    echo json_encode($fields);
    echo "Respuesta FCM:";
    $result = curl_exec($ch);
    echo $result;
    // Close connection
    curl_close($ch);
 ?>