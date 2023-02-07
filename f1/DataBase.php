<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }
	
	function signUp($table, $email, $username, $password)
	{
		$email = $this->prepareData($email);
		$username = $this->prepareData($username);
		$password = $this->prepareData($password);
		$password = password_hash($password, PASSWORD_DEFAULT);
		$this->sql = "INSERT INTO " . $table . "(email, username, password) VALUES('" . $email . "','" . $username . "','" . $password . "')";
		if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
	}

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "SELECT * FROM " . $table . " WHERE username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) > 0) {
            $this->data = mysqli_fetch_assoc($result);
            if (password_verify($password, $this->data['password'])) {
                return true;
            } else return false;
        } else return false;
    }
}
?>