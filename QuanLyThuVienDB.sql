-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: qlthuviendb
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `docgia`
--

DROP TABLE IF EXISTS `docgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docgia` (
  `maDocGia` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `namSinh` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`maDocGia`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docgia`
--

LOCK TABLES `docgia` WRITE;
/*!40000 ALTER TABLE `docgia` DISABLE KEYS */;
INSERT INTO `docgia` VALUES (1,'Đặng Chiến Thắng','Nam','02-04-2000','Nghệ An'),(2,'Nguyễn Vũ Minh Hoàng','Nam','28-10-2000','Đồng Nai'),(3,'Nguyễn Thị Hà My','Nữ','26-10-2000','Đồng Nai'),(4,'Đặng Thị Thảo Anh','Nữ','24-03-2005','HCM'),(6,'Hà Phạm Thu Hồng','Nữ','10-02-2000','169/124 tổ 18, khóm 3, phường Châu Phú A, Tp Châu Đốc, Tỉnh An Giang'),(7,'Mai Thị Ngọc Lan','Nữ','01-01-2000','HCM'),(15,'Nguyễn Văn An','Nam','10-11-2001','BRVT'),(16,'Đinh Khang An','Khác','17-11-2000','BRVT'),(18,'Trần Văn Tài','Khác','15-06-2001','Đồng Nai'),(25,'aa','aaa','29-03-2021','aaa'),(27,'Test them','Nam','28-10-2000','HCM'),(28,'RD1','Nam','28-10-2000','Hà Nội'),(29,'RD2','Nữ','28-10-2000','HCM'),(30,'Test them','Nam','28-10-2000','HCM'),(31,'RD1','Nam','28-10-2000','Hà Nội'),(32,'RD2','Nữ','28-10-2000','HCM');
/*!40000 ALTER TABLE `docgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `maNhanVien` int NOT NULL AUTO_INCREMENT,
  `hoTenNV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gioiTinhNV` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `namSinhNV` datetime NOT NULL,
  `diaChiNV` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenDangNhap` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `matKhau` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Hoang','Nam','2000-10-28 00:00:00','HCM','nhanvien1','123456789');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieumuon`
--

DROP TABLE IF EXISTS `phieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieumuon` (
  `maPhieuMuon` int NOT NULL AUTO_INCREMENT,
  `maDocGia` int NOT NULL,
  `maSach` int NOT NULL,
  `maNhanVien` int DEFAULT NULL,
  `soLuong` int NOT NULL,
  `tenDocGia` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSach` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tinhTrang` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayMuon` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hanTra` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayTra` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghiChu` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maPhieuMuon`),
  KEY `maDocGia_idx` (`maDocGia`),
  KEY `maSach_idx` (`maSach`),
  KEY `maNhanVien1_idx` (`maNhanVien`),
  CONSTRAINT `maDocGia1` FOREIGN KEY (`maDocGia`) REFERENCES `docgia` (`maDocGia`),
  CONSTRAINT `maNhanVien1` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`),
  CONSTRAINT `maSach1` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieumuon`
--

LOCK TABLES `phieumuon` WRITE;
/*!40000 ALTER TABLE `phieumuon` DISABLE KEYS */;
INSERT INTO `phieumuon` VALUES (1,1,1,1,1,'Đăng Chiến Thắng','Toán cao cấp 1','Đã trả','10-05-2021','09-06-2021','10-05-2021',NULL),(2,1,1,NULL,1,'Đăng Chiến Thắng','Toán cao cấp 1','Chưa trả','10-03-2021','09-04-2021',NULL,'Đã quá hạn'),(6,1,2,NULL,1,'Đăng Chiến Thắng','Toán cao cấp 2','Chưa trả','10-03-2021','09-04-2021',NULL,'Đã quá hạn'),(14,3,6,NULL,2,'Nguyễn Thị Hà My','Dược lâm sàng','Chưa trả','10-05-2021','09-06-2021',NULL,NULL),(15,2,4,NULL,3,'Nguyễn Vũ Minh Hoàng','Tôi tài giỏi, Bạn cũng thế','Chưa trả','10-05-2021','09-06-2021',NULL,NULL),(16,2,10,NULL,1,'Nguyễn Vũ Minh Hoàng','Lịch sử nghệ thuật phương tây','Đã trả','10-05-2021','09-06-2021','10-05-2021',NULL),(17,2,3,NULL,2,'Nguyễn Vũ Minh Hoàng','Để có trí nhớ tốt','Chưa trả','10-05-2021','09-06-2021',NULL,NULL),(18,2,3,NULL,2,'Nguyễn Vũ Minh Hoàng','Để có trí nhớ tốt','Chưa trả','10-05-2021','09-06-2021',NULL,NULL),(19,6,6,NULL,1,'Hà Phạm Thu Hồng','Dược lâm sàng','Đã trả','10-05-2021','09-06-2021','10-05-2021',NULL);
/*!40000 ALTER TABLE `phieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieutra`
--

DROP TABLE IF EXISTS `phieutra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieutra` (
  `maPhieuTra` int NOT NULL AUTO_INCREMENT,
  `maDocGia` int NOT NULL,
  `maSach` int NOT NULL,
  `ngayTra` datetime NOT NULL,
  `maNhanVien` int NOT NULL,
  PRIMARY KEY (`maPhieuTra`),
  KEY `idDocGia_idx` (`maDocGia`),
  KEY `idSach_idx` (`maSach`),
  KEY `maNhanVien2_idx` (`maNhanVien`),
  CONSTRAINT `maDocGia2` FOREIGN KEY (`maDocGia`) REFERENCES `docgia` (`maDocGia`),
  CONSTRAINT `maNhanVien2` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`),
  CONSTRAINT `maSach2` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieutra`
--

LOCK TABLES `phieutra` WRITE;
/*!40000 ALTER TABLE `phieutra` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieutra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `maSach` int NOT NULL AUTO_INCREMENT,
  `tenSach` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tacGia` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `theLoai` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nhaXuatBan` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `giaSach` decimal(10,0) NOT NULL,
  `soLuong` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`maSach`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (1,'Toán cao cấp 1','Nguyễn Đình Trí','Giáo trình','Giáo dục',15000,13),(2,'Toán cao cấp 2','Nguyễn Đình Trí','Giáo trình','Giáo dục',14500,51),(3,'Để có trí nhớ tốt','Vương Trung Hiếu','Tâm lý','Tổng hợp đồng nai',80000,60),(4,'Tôi tài giỏi, Bạn cũng thế','Adam Khoo','Tâm lý','Việt Nam',93600,120),(5,'Từ tơ lụa đến Silicon','Jeffrey E. Garten','Kinh tế','Trẻ',121700,50),(6,'Dược lâm sàng','Hoàng Thị Kim Huyền','Y học','Y Học',250000,18),(7,'Bào chế đông dược','Ts. Nguyễn Đức Quang','Y học','Y học',120000,21),(8,'Nguyên lí Mac-Lenin I','Nguyễn Ngọc Long','Giáo trình','Giáo dục',30000,200),(9,'Nguyên lí Mac-Lenin II','Nguyễn Ngọc Long','Giáo trình','Giáo dục',30000,160),(10,'Lịch sử nghệ thuật phương tây','Michael Levey','Nghệ thuật','Văn Lang',75000,12),(11,'Võ thuật Aikido','Oratti','Thể thao','Hoàng Minh',100000,33),(12,'Thần, người và đất Việt','Tạ Chí Đại Trường','Lịch sử','Văn học',56700,77),(13,'Hồ Chí Minh toàn tập','Đặng Chiến Thắng','Lịch sử','Chính trị quốc gia',123400,120),(14,'Chiếc ngai vàng','Lan Khai','Văn học Việt Nam','Văn học',97000,11),(15,'Ranh giới','Rain8x','Văn học Việt Nam','Văn học',44500,5),(16,'Chị em khác mẹ','Thụy Ý','Văn học Việt Nam','Văn học',37000,6),(17,'Xác xuất thống kê','ThS. Lý Chính Thắng','Giáo trình','Giáo dục',40000,55),(18,'Lập Trình Java','Dương Hữu Thành','Giáo trình','Giáo dục',100000,100),(19,'Bách khoa về vitamin','BS Thu Minh','Y học','Y học',22500,31),(20,'Trảm Long','Hồng Trần','Tiểu thuyết','Văn học',200000,12),(21,'Đông Chu Liệt Quốc','Phùng Mộng Long','Tiểu thuyết','Văn học',195000,27),(23,'Dinh dưỡng cho trẻ béo phì','Việt Điền','Ẩm thực','Văn Lang',22000,14),(24,'Bánh phương Tây','Nguyễn Thu Dung','Ẩm thực','Hồ Chí Minh',35500,20),(25,'Tiền không mua được gì?','Michael Sandel','Kinh tế','Trẻ',85000,111),(26,'Bí mật của vua Solomon','Bruce Fleet - Alton Gansky','Kinh tế','Trẻ',42920,121),(27,'Test thêm sách','Adam Khoo','Tâm lý','Việt Nam',10000,123),(28,'S1','Thang','Truyện tranh','VN',200000,123),(29,'S2','Hoang','Cổ tích','VN',200000,45),(30,'Test thêm sách','Adam Khoo','Tâm lý','Việt Nam',10000,123),(31,'S1','Thang','Truyện tranh','VN',200000,123),(33,'S1','Thang','Truyện tranh','VN',200000,123),(35,'Test thêm sách','Adam Khoo','Tâm lý','Việt Nam',10000,123),(36,'S1','Thang','Truyện tranh','VN',200000,123),(37,'S2','Hoang','Cổ tích','VN',200000,45);
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 22:39:27
