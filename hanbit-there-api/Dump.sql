-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hanbit_there
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_info`
--

DROP TABLE IF EXISTS `tbl_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_info` (
  `id` varchar(150) NOT NULL,
  `info_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `value` varchar(500) NOT NULL,
  `info_order` int(11) NOT NULL,
  PRIMARY KEY (`id`,`info_id`),
  CONSTRAINT `fk_tbl_info_tbl_there1` FOREIGN KEY (`id`) REFERENCES `tbl_there` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_info`
--

LOCK TABLES `tbl_info` WRITE;
/*!40000 ALTER TABLE `tbl_info` DISABLE KEYS */;
INSERT INTO `tbl_info` VALUES ('Guam',1,'언어','영어',1),('Guam',2,'화폐','달러(USD, Doller)',2),('Guam',3,'비자','45일 미만 무비자 체류',3),('Guam',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4),('Hawaii',1,'언어','영어',1),('Hawaii',2,'화폐','달러(USD, Dollar)',2),('Hawaii',3,'비자','45일 미만 무비자 체류',3),('Hawaii',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4),('LasVegas',1,'언어','영어',1),('LasVegas',2,'화폐','달러(USD, Dollar)',2),('LasVegas',3,'비자','90일 이하 체류시 ESTA 로 무비자 입국 가능',3),('LasVegas',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4),('Newyork',1,'언어','영어',1),('Newyork',2,'화폐','US달러 (USD)',2),('Newyork',3,'비자','90일 이하 체류시 ESTA 로 무비자 입국 가능',3),('Newyork',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4),('Saipan',1,'언어','영어',1),('Saipan',2,'화폐','달러(USD, Dollar)',2),('Saipan',3,'비자','45일 미만 무비자 체류',3),('Saipan',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4),('San-Francisco',1,'언어','영어',1),('San-Francisco',2,'화폐','달러(USD, Dollar)',2),('San-Francisco',3,'비자','90일 이하 체류시 ESTA 로 무비자 입국 가능',3),('San-Francisco',4,'TIP','110v이므로 변환플러그(돼지코)가 필요',4);
/*!40000 ALTER TABLE `tbl_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_location`
--

DROP TABLE IF EXISTS `tbl_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_location` (
  `id` varchar(150) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tbl_location_tbl_there` FOREIGN KEY (`id`) REFERENCES `tbl_there` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_location`
--

LOCK TABLES `tbl_location` WRITE;
/*!40000 ALTER TABLE `tbl_location` DISABLE KEYS */;
INSERT INTO `tbl_location` VALUES ('Guam',13.444304,144.793732),('Hawaii',21.3165109,-157.8487859),('LasVegas',36.14211,-115.1324358),('Newyork',40.73061,-73.935242),('Saipan',15.1780588,145.7511158),('San-Francisco',37.7699955,-122.4196075);
/*!40000 ALTER TABLE `tbl_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_there`
--

DROP TABLE IF EXISTS `tbl_there`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_there` (
  `id` varchar(150) NOT NULL,
  `name` varchar(150) NOT NULL,
  `name_en` varchar(150) NOT NULL,
  `background` varchar(500) NOT NULL,
  `summary` varchar(500) NOT NULL,
  `timezone` varchar(100) NOT NULL,
  `group_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tbl_there_group_id_idx` (`group_id`),
  CONSTRAINT `fk_tbl_there_group_id` FOREIGN KEY (`group_id`) REFERENCES `tbl_there_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_there`
--

LOCK TABLES `tbl_there` WRITE;
/*!40000 ALTER TABLE `tbl_there` DISABLE KEYS */;
INSERT INTO `tbl_there` VALUES ('adelaide','애들레이드','Adelaide','./img/theres/adelaide.jpg','','Australia/Adelaide','Oceania'),('Bali','발리','Bali','./img/theres/Bali.jpg','','Asia/Makassar','Asia'),('BangKok','방콕','BangKok','./img/theres/BangKok.jpg','','Asia/Bangkok','Asia'),('Barcelona','바르셀로나','Barcelona','./img/theres/Barcelona.jpg','','Europe/Madrid','Europe'),('Boracay','보라카이','Boracay','./img/theres/Boracay.jpg','','Asia/Manila','Asia'),('Brisbane','브리즈번','Brisbane','./img/theres/Brisbane.jpg','','Australia/Brisbane','Oceania'),('Budapest','부다페스트','Budapest','./img/theres/Budapest.jpg','','Europe/Budapest','Europe'),('busan','부산','Busan','./img/theres/busan.jpg','','Asia/Seoul','Korea'),('Cebu','세부','Cebu','./img/theres/Cebu.jpg','','Asia/Manila','Asia'),('Chiang-Mai','치앙마이','Chiang-Mai','./img/theres/Chiang-Mai.jpg','','Asia/Bangkok','Asia'),('Chungcheong','충청','Chungcheong','./img/theres/Chungcheong.jpg','','Asia/Seoul','Korea'),('daejeon','대전','Daejeon','./img/theres/daejeon.jpg','','Asia/Seoul','Korea'),('Danang','다낭','Danang','./img/theres/Danang.jpg','','Asia/Ho_Chi_Minh','Asia'),('darwin','다윈','Darwin','./img/theres/darwin.jpg','','Australia/Darwin','Oceania'),('Florence','피렌체','Florence','./img/theres/Florence.jpg','','Europe/Rome','Europe'),('Fukuoka','후쿠오카','Fukuoka','./img/theres/Fukuoka.jpg','','Asia/Tokyo','Asia'),('gangwon','강원','Gangwon','./img/theres/gangwon.jpg','','Asia/Seoul','Korea'),('Gold-Coast','골드코스트','Gold-Coast','./img/theres/Gold-Coast.jpg','','Australia/Sydney','Oceania'),('Guam','괌','Guam','./img/theres/Guam.jpg','남태평양 푸른 바다의 넘실거림을 보고있자니<br>어제가 오늘인 듯, 오늘이 어제인 듯 시간의 흐름이 느껴지지 않는곳, 괌','Pacific/Guam','America'),('gyeonggi','경기','Gyeonggi','./img/theres/gyeonggi.jpg','','Asia/Seoul','Korea'),('gyeongsang','경상','Gyeongsang','./img/theres/gyeongsang.jpg','','Asia/Seoul','Korea'),('Hawaii','하와이','Hawaii','./img/theres/Hawaii.jpg','시원하게 뻗은 야자수와 말없이 걷기만해도 좋을 것 같은 와이키키 비치.<br>다양한 인종, 문화, 그보다 더 다양한 자연환경이 절묘하게 어울리는 레인보우 스테이트','Pacific/Honolulu','America'),('Hokkaido','훗카이도','Hokkaido','./img/theres/Hokkaido.jpg','','Asia/Tokyo','Asia'),('HongKong','홍콩','HongKong','./img/theres/HongKong.jpg','','Asia/Hong_Kong','Asia'),('incheon','인천','Incheon','./img/theres/incheon.jpg','','Asia/Seoul','Korea'),('Interlaken','인터라켄','Interlaken','./img/theres/Interlaken.jpg','','Europe/Zurich','Europe'),('Jeju','제주','Jeju','./img/theres/Jeju.jpg','','Asia/Seoul','Korea'),('jeolla','전라','Jeolla','./img/theres/jeolla.jpg','','Asia/Seoul','Korea'),('Kotakinabalu','코타키나발루','Kotakinabalu','./img/theres/Kotakinabalu.jpg','','Asia/Kuching','Asia'),('Kualalumpur','쿠알라룸푸르','Kualalumpur','./img/theres/Kualalumpur.jpg','','Asia/Kuala_Lumpur','Asia'),('Laos','라오스','Laos','./img/theres/Laos.jpg','','Asia/Vientiane','Asia'),('LasVegas','라스베가스','LasVegas','./img/theres/LasVegas.jpg','호화스러운 도시, 24시간 화려한 조명이 가득 메운 환락의 도시','America/Tijuana','America'),('Lisbon','리스본','Lisbon','./img/theres/Lisbon.jpg','','Europe/Lisbon','Europe'),('London','런던','London','./img/theres/London.jpg','','Europe/London','Europe'),('Macau','마카오','Macau','./img/theres/Macau.jpg','','Asia/Macau','Asia'),('Madrid','마드리드','Madrid','./img/theres/Madrid.jpg','','Europe/Madrid','Europe'),('Melbourne','멜버른','Melbourne','./img/theres/Melbourne.jpg','','Australia/Melbourne','Oceania'),('Mongolia','몽골','Mongolia','./img/theres/Mongolia.jpg','','Asia/Ulaanbaatar','Asia'),('naples','나폴리','Naples','./img/theres/naples.jpg','','Europe/Rome','Europe'),('Newyork','뉴욕','Newyork','./img/theres/Newyork.jpg','오늘은 내 남은 인생의 첫날이다<br>-센트럴파크의 어느 벤치에 누군가가 세긴 낙서','America/New_York','America'),('NewZealand','뉴질랜드','NewZealand','./img/theres/NewZealand.jpg','','Pacific/Auckland','Oceania'),('Okinawa','오키나와','Okinawa','./img/theres/Okinawa.jpg','','Asia/Tokyo','Asia'),('Osaka','오사카','Osaka','./img/theres/Osaka.jpg','','Asia/Tokyo','Asia'),('Paris','파리','Paris','./img/theres/Paris.jpg','','Europe/Paris','Europe'),('perth','퍼스','Perth','./img/theres/perth.jpg','','Australia/Perth','Oceania'),('Phuket','푸켓','Phuket','./img/theres/Phuket.jpg','','Asia/Bangkok','Asia'),('qingdao','칭다오','Qingdao','./img/theres/qingdao.jpg','','Asia/Shanghai','Asia'),('Rome','로마','Rome','./img/theres/Rome.jpg','','Europe/Rome','Europe'),('Saipan','사이판','Saipan','./img/theres/Saipan.jpg','늘 혼자 여행하는게 좋았지만<br>꼭 가족과 함께 다시 오고 싶은 곳이 있었다.<br>사이판. 사이판이 내게 그랬다.','Pacific/Saipan','America'),('San-Francisco','샌프란시스코','San-Francisco','./img/theres/San-Francisco.jpg','금문교로 대표되는, 아름다움과 낭만의 도시','America/Los_Angeles','America'),('seoul','서울','Seoul','./img/theres/seoul.jpg','','Asia/Seoul','Korea'),('Shanghai','상해','Shanghai','./img/theres/Shanghai.jpg','','Asia/Shanghai','Asia'),('Siemreap','시엠립','Siemreap','./img/theres/Siemreap.jpg','','Asia/Phnom_Penh','Asia'),('Singapore','싱가폴','Singapore','./img/theres/Singapore.jpg','','Asia/Singapore','Asia'),('Sydney','시드니','Sydney','./img/theres/Sydney.jpg','','Australia/Sydney','Oceania'),('Taiwan','대만','Taiwan','./img/theres/Taiwan.jpg','','Asia/Taipei','Asia'),('tasmania','타즈매니아','Tasmania','./img/theres/tasmania.jpg','','Australia/Sydney','Oceania'),('Tokyo','도쿄','Tokyo','./img/theres/Tokyo.jpg','','Asia/Tokyo','Asia'),('uluru','울룰루','Uluru','./img/theres/uluru.jpg','','Australia/Sydney','Oceania'),('Venice','베네치아','Venice','./img/theres/Venice.jpg','','Europe/Rome','Europe'),('vienna','비엔나','Vienna','./img/theres/vienna.jpg','','Europe/Vienna','Europe');
/*!40000 ALTER TABLE `tbl_there` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_there_group`
--

DROP TABLE IF EXISTS `tbl_there_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_there_group` (
  `id` varchar(100) NOT NULL,
  `name` varchar(150) NOT NULL,
  `group_order` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_there_group`
--

LOCK TABLES `tbl_there_group` WRITE;
/*!40000 ALTER TABLE `tbl_there_group` DISABLE KEYS */;
INSERT INTO `tbl_there_group` VALUES ('America','아메리카',1),('Asia','아시아',2),('Europe','유럽',3),('Korea','국내',5),('Oceania','오세아니아',4);
/*!40000 ALTER TABLE `tbl_there_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_traffic`
--

DROP TABLE IF EXISTS `tbl_traffic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_traffic` (
  `id` varchar(150) NOT NULL,
  `traffic_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `icon` varchar(45) NOT NULL,
  `contents` varchar(500) NOT NULL,
  `traffic_order` int(11) NOT NULL,
  PRIMARY KEY (`id`,`traffic_id`),
  CONSTRAINT `fk_tbl_traffic_tbl_there1` FOREIGN KEY (`id`) REFERENCES `tbl_there` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_traffic`
--

LOCK TABLES `tbl_traffic` WRITE;
/*!40000 ALTER TABLE `tbl_traffic` DISABLE KEYS */;
INSERT INTO `tbl_traffic` VALUES ('Guam',1,'레드 괌 셔틀 버스','bus','주요 호텔과 쇼핑센터는 물론 유명 레스토랑까지 노선에 포함돼 있어 유용하다.',1),('Guam',2,'택시 Taxi','taxi','호텔이나 쇼핑센터 같은 지정된 지점에서 탑승. 팁은 요금의 10%면 적당',2),('Guam',3,'투몬 셔틀 버스','bus','투몬과 타무닝 지역을 연결하며 북부, 남부노선이 있고 약 8분 간격 운행',3),('Guam',4,'렌트카 Rent-a-Car','car','괌 자유여행자들에게 최고의 교통수단이다. 그러다보니, 여행 전 예약해서 준비해둘것.',4),('Guam',5,'갤러리아 무료 셔틀 버스','bus','투몬 지역과 타무닝 지역의 주요 호텔을 3개의 노선이 운행',5),('Hawaii',1,'트롤리 Trolley','bus','가격 비싸고 에어콘 시설 없어서 추천하지는 않음. 창문이 없어 시원한 바람 맞으며 달리는 기분은 최고!',1),('Hawaii',2,'택시 Taxi','taxi','인원이 많으면 많을수록 유리한 교통수단. 요금이 비싸고 팁을 챙겨줘야 함을 잊지 말것',2),('Hawaii',3,'더버스 The Bus','bus','가격이 비교적 저렴하고 환승도 무료라 자유여행자들이 선호하는 교통수단',3),('Hawaii',4,'렌트카 Rent-a-Car','car','시간의 효율적 차원에서 하와이에서 가장 유리한 교통수단. 여건이 된다면 가장 권한다.',3),('LasVegas',1,'트램','subway','스트립 중심 도심위로 다닌다. 호텔들을 통과하기도 하며 무료로 이용가능하다.',1),('LasVegas',2,'UBER','taxi','택시로 이용이 편리하지만, 공항 내와 공항으로 가는 길에는 우버 택시를 이용할 수 없다.',2),('LasVegas',3,'RTC','bus','DEUCE / SDX / WAX / CX / DVX 다섯가지를 모두 이용할 수 있는 통합 버스. 제한된 시간 내에 무한적으로 이용 가능하다.',3),('Newyork',1,'지하철(Subway)','subway','뉴욕 메트로의 첫번째 대표주자 지하철. 지하철 내부에서 공연을 즐길 수 있는 뉴욕의 서브웨이.',1),('Newyork',2,'기차(LIRR)','train','뉴욕의 곳곳으로 다니는 \'럴\'이라고 불리는 뉴욕 기차. 따로 무제한 패스가 있으니 여행 떠나기전 꼭 확인할 것',2),('Newyork',3,'버스(Bus)','bus','버스 정류장에서 문자를 보내면 버스 도착시간 답장을 받아 확인할 수 있다.',3),('Saipan',1,'이브닝 쇼핑셔틀 Bus','bus','사이판 북부 쪽에 위치한 각 호텔과 DFS갤러리아를 연결하는 유료 순환 셔틀 버스',1),('Saipan',2,'택시 Taxi','taxi','가격을 미리 흥정하여 시간제로 원하는 여행지를 돌아보는걸 추천한다.',2),('Saipan',3,'DFS 갤러리아 익스프레스 Bus','bus','사이판 내 주요 호텔들과 가라판 중심에 위치한 DFS갤러리아를 연결하는 무료 셔틀버스',3),('Saipan',4,'렌트카 Rent-a-Car','car','자유여행자라면 가장 좋은 선택이다.',3),('San-Francisco',1,'MUNI METRO','subway','샌프란시스코 시내에서만 운영하는 전차',1),('San-Francisco',2,'MUNI BUS','bus','시내의 구석구석을 연결하는 버스, 노선이 복잡하므로 버스 노선도는 필수',2),('San-Francisco',3,'Cable Car','train','다운타운에서 Fisherman’s Wharf까지 이동할 때 유용',3),('San-Francisco',4,'BART','subway','한국식 지상/지하 전철, 샌프란시스코 시내를 벗어나 Bay Area의 East Bay쪽 도시를 갈 때 유용',3);
/*!40000 ALTER TABLE `tbl_traffic` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-19 17:07:45
