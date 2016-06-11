<?php
require_once '../db_connection.php';

// buat array untuk menampung respon dari JSON
$respon = array ();

// cek apakah nilai yang dikirimkan android sudah terisi
if (isset($_POST['idmem']) && isset($_POST['nama']) && isset($_POST['alamat'])) {
	
	$idmem = $_POST['idmem'];
	$name = $_POST['nama'];
	$alamat = $_POST['alamat'];
	
	// query update berdasarkan id
	$sql = "UPDATE `inventory_km`.`member`
SET `nama` = '$name',
  `alamat` = '$alamat'
WHERE `id` = '$idmem';";
	$result = mysqli_query ( $con, $sql );
	
	// cek apakah berhasil update atau tidak
	if ($result) {
		// jika sukses diupdate
		$respon ["sukses"] = 1;
		$respon ["pesan"] = "Data member berhasil diupdate.";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	} else {
		// gagal update data
		$respon ["sukses"] = 0;
		$respon ["pesan"] = "Gagal update data.";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon ["sukses"] = 0;
	$respon ["pesan"] = "data belum terset/terisi";
	
	// memprint/mencetak JSON respon
	echo json_encode ( $respon );
}