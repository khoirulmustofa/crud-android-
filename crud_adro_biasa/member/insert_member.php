<?php
require_once '../db_connection.php';
//buat array untuk menampung respon dari JSON
$respon = array();

// cek apakah nilai yang dikirimkan android sudah terisi
if (isset($_POST['nama']) && isset($_POST['alamat'])) {

	$name = $_POST['nama'];
	$alamat = $_POST['alamat'];
		
	// query menambah data member
	$sql="INSERT INTO `inventory_km`.`member`
            (`nama`,
             `alamat`)
VALUES ('$name',
        '$alamat');";
	$result = mysqli_query($con, $sql) or die(mysqli_error($con));
	// cek apakah query berhasil menambah data
	if ($result) {
		// jika berhasil menambah data ke mysql
		$respon["sukses"] = 1;
		$respon["pesan"] = "Berhasil menambah data member.";

		// memprint/mencetak JSON respon
		echo json_encode($respon);
	} else {
		// gagal menambah data member
		$respon["sukses"] = 0;
		$respon["pesan"] = "Gagal menambah data.";

		// memprint/mencetak JSON respon
		echo json_encode($respon);
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon["sukses"] = 0;
	$respon["pesan"] = "data belum terisi";

	//  memprint/mencetak JSON respon
	echo json_encode($respon);
}