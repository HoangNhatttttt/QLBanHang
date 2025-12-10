-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2025 at 05:58 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thietbidientu`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `MaCTKM` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `PhanTramGiamGia` decimal(5,2) DEFAULT NULL CHECK (`PhanTramGiamGia` >= 0 and `PhanTramGiamGia` <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`MaCTKM`, `MaSP`, `PhanTramGiamGia`) VALUES
('KM1', '1', 10.00),
('KM1', '16', 7.00),
('KM1', '3', 10.00),
('KM2', '13', 15.00),
('KM2', '14', 12.00),
('KM2', '17', 7.00),
('KM2', '4', 7.00),
('KM3', '10', 12.00),
('KM4', '2', 20.00),
('KM4', '6', 20.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('PN1', '1', 15, 27000000.00, 405000000.00),
('PN2', '13', 15, 19000000.00, 285000000.00),
('PN2', '14', 10, 20000000.00, 200000000.00),
('PN2', '4', 12, 21000000.00, 252000000.00),
('PN3', '15', 7, 12000000.00, 84000000.00),
('PN3', '5', 12, 7000000.00, 84000000.00),
('PN4', '15', 12, 12000000.00, 144000000.00),
('PN4', '18', 5, 32000000.00, 160000000.00),
('PN4', '5', 20, 7000000.00, 140000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `chtrinhkhuyenmai`
--

CREATE TABLE `chtrinhkhuyenmai` (
  `MaCTKM` varchar(10) NOT NULL,
  `TenKhuyenMai` varchar(255) NOT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chtrinhkhuyenmai`
--

INSERT INTO `chtrinhkhuyenmai` (`MaCTKM`, `TenKhuyenMai`, `NgayBatDau`, `NgayKetThuc`) VALUES
('KM1', 'SALE IPHONE GIÁNG SINH', '2025-12-01', '2025-12-31'),
('KM2', 'SALE SAMSUNG', '2025-12-09', '2025-12-24'),
('KM3', 'ONESALE ONEPLUS', '2025-12-11', '2025-12-17'),
('KM4', 'FURY OF THE SMALL PHONES', '2025-12-10', '2025-12-20');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaKH` varchar(10) DEFAULT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `NgayLapHD` datetime DEFAULT current_timestamp(),
  `TongTien` decimal(15,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `Ho`, `Ten`, `DiaChi`, `SDT`) VALUES
('1', 'Nguyễn', 'An', 'Hà Nội', '0987654321');

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLoai` varchar(10) NOT NULL,
  `TenLoai` varchar(100) NOT NULL,
  `MoTa` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`MaLoai`, `TenLoai`, `MoTa`) VALUES
('1', 'Apple', 'Apple stuff'),
('2', 'Asus', 'Zen phone stuff'),
('3', 'OnePlus', 'OnePlus stuff'),
('4', 'Samsung', 'Samsung stuff'),
('5', 'XiaoMi', 'XiaoMi Stuff'),
('6', 'Google', 'Pixel Stuff');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(10) NOT NULL,
  `TenNCC` varchar(255) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`) VALUES
('1', 'Công ty A', 'TPHCM', '0912345670'),
('2', 'Công ty TNHH Hữu Thiện', '210/30 Lê Lợi', '0981537266'),
('3', 'Doanh nghiệp Tư nhân Hữu Tiến', '31/2A Lê Duẩn', '0871653987');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `LuongThang` decimal(10,2) NOT NULL,
  `TrangThai` int(10) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `Ho`, `Ten`, `LuongThang`, `TrangThai`) VALUES
('1', 'Trần', 'Bình', 15000000.00, 0),
('2', 'kok', 'kok', 1.00, 0),
('3', 'Nguyen', 'Van Chi', 8000000.00, 0),
('4', 'Hoang', 'Sang', 0.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) NOT NULL,
  `TenQuyen` varchar(50) NOT NULL,
  `ChiTietQuyen` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
('Q1', 'Quản lí', 'qlNhanVien qlKhachHang, qlHoaDon'),
('Q2', 'Admin', 'qlTaiKhoan qlQuyen'),
('Q3', 'Sản phẩm', 'qlSanPham, qlLoaiSanPham, qlKhuyenMai'),
('Q4', 'nhập hàng', 'qlNhapHang, qlPhieuNhap, qlNhaCungCap');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `MaPN` varchar(10) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `MaNCC` varchar(10) DEFAULT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` decimal(15,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`MaPN`, `MaNV`, `MaNCC`, `NgayNhap`, `GioNhap`, `TongTien`) VALUES
('PN1', '4', '1', '2025-12-10', '11:35:21', 405000000.00),
('PN2', '4', '3', '2025-12-10', '11:35:56', 737000000.00),
('PN3', '4', '2', '2025-12-10', '11:36:33', 168000000.00),
('PN4', '4', '2', '2025-12-10', '11:57:49', 444000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `TenSP` varchar(255) NOT NULL,
  `SoLuong` int(11) DEFAULT 0,
  `DonGia` decimal(10,2) NOT NULL,
  `DonVi` varchar(50) DEFAULT NULL,
  `MaLoai` varchar(10) DEFAULT NULL,
  `HinhAnh` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `SoLuong`, `DonGia`, `DonVi`, `MaLoai`, `HinhAnh`) VALUES
('1', 'iPhone 15', 15, 27000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\items\\iphone15.jpg'),
('10', 'OnePlus 13', 0, 19000000.00, 'Chiếc', '3', 'src\\GUI\\Image\\items\\oneplus-13.png'),
('13', 'Samsung Galaxy S20 Ultra Black', 15, 19000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\items\\Gallery-01-C2-Lockup-MysticBlack-1600x1200-3341909277.jpg'),
('14', 'Samsung Galaxy S20 Ultra White', 10, 20000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\items\\csm_4_3_Teaser_Samsung_Galaxy_Note20_Ultra_5G_SM-N986B_MysticWhite_f4a260a140-3642743396.jpg'),
('15', 'Xiaomi Redmi note 14', 12, 12000000.00, 'chiếc', '5', 'src\\GUI\\Image\\items\\Redmi-Note-14-4G-Azul-oceano-1803113835.png'),
('16', 'iPhone 15 Pro Max', 0, 32000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\items\\iphone-15-pro-max.png'),
('17', 'Samsung Z Flip 4', 0, 45000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\items\\samsung-galaxy-z-flip4-graphite.png'),
('18', 'Xiaomi Mix Fold 3', 5, 32000000.00, 'Chiếc', '5', 'src\\GUI\\Image\\items\\xiaomi-mix-fold-3.jpg'),
('19', 'Google Pixel 8a', 0, 25000000.00, 'Chiếc', '6', 'src\\GUI\\Image\\items\\pixel-8a.jpg'),
('2', 'Zenfone 9', 0, 22000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\items\\ZF9-blue.jpg'),
('20', 'Google Pixel 9', 0, 36000000.00, 'Chiếc', '6', 'src\\GUI\\Image\\items\\pixel-9.jpg'),
('3', 'iPhone 14 Pro', 20, 25000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\items\\iphone14-pro.jpg'),
('4', 'Samsung Galaxy S23', 12, 21000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\items\\galaxy-s23.jpg'),
('5', 'Xiaomi Redmi Note 12', 32, 7000000.00, 'Chiếc', '5', 'src\\GUI\\Image\\items\\redmi-note12.jpg'),
('6', 'Zenfone 8', 0, 11000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\items\\zenfone-8.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTK` varchar(50) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `MaQuyen` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`TenTK`, `MatKhau`, `MaNV`, `MaQuyen`) VALUES
('admin', 'admin', '1', 'Q2'),
('nhaphang', 'nhaphang', '4', 'Q4'),
('quanly', 'quanly', '1', 'Q1'),
('sanpham', 'sanpham', '3', 'Q3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD PRIMARY KEY (`MaCTKM`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MaPN`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chtrinhkhuyenmai`
--
ALTER TABLE `chtrinhkhuyenmai`
  ADD PRIMARY KEY (`MaCTKM`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Indexes for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaNCC` (`MaNCC`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTK`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaQuyen` (`MaQuyen`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_1` FOREIGN KEY (`MaCTKM`) REFERENCES `chtrinhkhuyenmai` (`MaCTKM`),
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaPN`) REFERENCES `phieunhaphang` (`MaPN`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `phieunhaphang_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `phieunhaphang_ibfk_2` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaisp` (`MaLoai`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
