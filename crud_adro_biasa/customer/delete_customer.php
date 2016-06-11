<?php
require_once '../db_connection.php';

// buat array untuk menampung respon dari JSON
$response = array ();

// cek apakah variabel idmem sudah terset / terisi
if (isset ( $_POST ['id'] )) {
	$id = $_POST ['id'];
	
	// query update member berdasarkan id
	$sql = "DELETE
FROM `inventory_km`.`customer`
WHERE `id` = '$id'";
	$result = mysqli_query ( $con, $sql );
	
	// jika berhasil di hapus
	if (mysqli_affected_rows () > 0) {
		$respon ["sukses"] = 1;
		$respon ["pesan"] = "Customer berhasil dihapus";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	} else {
		// jika gagal dihapus
		$respon ["sukses"] = 0;
		$respon ["pesan"] = "Gagal dihapus";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon ["sukses"] = 0;
	$respon ["pesan"] = "data belum terisi";
	
	// memprint/mencetak JSON respon
	echo json_encode ( $respon );
}