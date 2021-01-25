<?php  
    $result = array();

    switch($_POST['op']) 
    {
        case '1':
            actualizarGrupo();
            break;
        case '2':
            actualizarUsuario();
        default:
            # code...
            break;
    }

    function actualizarGrupo()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "UPDATE `grupo` SET `nombre`='".$_POST['nombre']."',`descripcion`='".$_POST['descricion']."' WHERE `idGrupo` = '".$_POST['ID']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-
    function actualizarUsuario()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "UPDATE `usuario` SET `token`='".$_POST['token']."' WHERE idUsuario = '".$_POST['ID']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            echo "1";
        }
        else
        {
            echo "-1";
        }
        
        
        mysqli_close($conexion);
    }
?>