<?php  
	$conexion = new mysqli("localhost","root","","mydb");

	$result = array();
	$result['usuario'] = array();

	$select = "SELECT * FROM usuario WHERE boleta='".$_GET['usuario']."' AND token='".$_GET['clave']."'";
    $mostrar = mysqli_query($conexion,$select);
        

    if($mostrar) 
    {
        while($row = mysqli_fetch_array($mostrar))
        {
            $index['idUsuario'] = $row['0'];
            $index['nombrecompleto'] = $row['1'];
            $index['boleta'] = $row['2'];
            $index['token'] = $row['3'];
            $index['tipo'] = $row['4'];
            $index['Programa_idPrograma'] = $row['5'];

            array_push($result['usuario'], $index);
        }

        if(sizeof($result['usuario']) > 0)
        {
            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
    }
    else
    {
        $result["success"]="0";
    }

    echo json_encode($result, JSON_UNESCAPED_UNICODE);
    mysqli_close($conexion);
?>