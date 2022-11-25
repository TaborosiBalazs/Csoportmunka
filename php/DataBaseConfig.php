<?php
class DataBaseConfig
{
	public $servername;
	public $username;
	public $password;
	public $databasename;
	
	public function __construct(){
		$this->servername = '159.69.179.95';
		$this->username = 'root';
		$this->password = '123456789';
		$this->databasename = 'f1';
	}
}
?>