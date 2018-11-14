import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { reject } from 'q';
import { Utils } from 'helpers/utils';
import { Router } from '@angular/router';
declare var $: any;
@Injectable()
export class Tsl010200Service {

  risk = {
    riskCode1: 1,
    riskCode2: 2,
    riskCode3: 3,
    riskDesc1: "ความถี่ของเดือนที่ชำระภาษี",
    riskDesc2: "เปรียบเทียบจำนวนภาษีระหว่างเดือน",
    riskDesc3: "เปรียบเทียบความแต่งต่างการชำระภาษีระหว่างปี"
  }
  tableC: any;
  tableC2y: any;
  dataList: any[] = [];
  // ==>params  
  table: any;
  table2: any;
  jsonColumn: any = "";
  month: any = [
    'ม.ค.',
    'ก.พ.',
    'มี.ค.',
    'เม.ย.',
    'พ.ค.',
    'มิ.ย.',
    'ก.ค.',
    'ส.ค.',
    'ก.ย.',
    'ต.ค.',
    'พ.ย.',
    'ธ.ค.',
  ]
  dataSelect: any;
  data: any = [];
  dataTableCompare: any = [
    {
      "exciseId": "0105551119601-1-001",
      "conpanyName": "บริษัท เอ็มทีพี เอชพี เจวี (ประเทศไทย) จำกัด",
      "address": "555 อาคารเวฟเพลส ชั้น11 และ 16 หมู่ที่- ซอย- ถนนวิทยุ แขวงลุมพินี เขตปทุมวัน  จังหวัดกรุงเทพมหานคร 10330",
      "subProduct": "แบตเตอรี่",
      "sector": "ภาคที่ 1",
      "area": "อ่างทอง",
      "month1": "-",
      "month2": "-",
      "month3": "148889",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "0",
      "month8": "198133",
      "month9": "214710.92",
      "month10": "0",
      "month11": "0",
      "month12": "313131",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-100.0",
      "pack8": "7.721041854787829",
      "pack9": "100.0",
      "pack10": "0.0",
      "pack11": "-100.0",
      "pack12": null
    },
    {
      "exciseId": "0105533135161-1-001",
      "conpanyName": "บริษัท ยูนิไทย ชิปยาร์ด แอนด์ เอนจิเนียริ่ง จำกัด",
      "address": "25 อาคารอัลมาลิงค์ ชั้น11 ซอยชิดลม ถนนเพลินจิต แขวงลุมพินี เขตปทุมวัน  จังหวัดกรุงเทพมหานคร 10330",
      "subProduct": "ไนท์คลับและดิสโกเธค",
      "sector": "ภาคที่ 1",
      "area": "ชัยนาท",
      "month1": "-",
      "month2": "-",
      "month3": "0",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "0",
      "month8": "1770.88",
      "month9": "0",
      "month10": "0",
      "month11": "0",
      "month12": "0",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-100.0",
      "pack8": "100.0",
      "pack9": "0.0",
      "pack10": "0.0",
      "pack11": "0.0",
      "pack12": null
    },
    {
      "exciseId": "0105533053629-1-001",
      "conpanyName": "บริษัท ไทย เทรดดิ้ง ออยล์ (1990) จำกัด",
      "address": "3/18 อาคาร- ชั้น- หมู่ที่8 ซอยจรัญสนิทวงศ์ 13 ถนนจรัญสนิทวงศ์ แขวงบางแวก เขตภาษีเจริญ  จังหวัดกรุงเทพมหานคร 10160",
      "subProduct": "แก้วและเครื่องแก้ว",
      "sector": "ภาคที่ 1",
      "area": "ชัยนาท",
      "month1": "-",
      "month2": "-",
      "month3": "3710",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "3900",
      "month8": "5230",
      "month9": "3750",
      "month10": "3940",
      "month11": "2530",
      "month12": "2260",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "25.430210325047803",
      "pack8": "-39.46666666666667",
      "pack9": "4.822335025380711",
      "pack10": "-55.73122529644269",
      "pack11": "-11.946902654867257",
      "pack12": null
    },
    {
      "exciseId": "0103537011147-1-002",
      "conpanyName": "ห้างหุ้นส่วนจำกัด ซี.เอ็ม.อินเตอร์ แพ็ค",
      "address": "301/58-59 หมู่ที่6 ถนนพหลโยธิน แขวงอนุสาวรีย์ เขตบางเขน  จังหวัดกรุงเทพมหานคร 10220",
      "subProduct": "แก้วและเครื่องแก้ว",
      "sector": "ภาคที่ 1",
      "area": "ชัยนาท",
      "month1": "-",
      "month2": "-",
      "month3": "12805.85",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "13091.4",
      "month8": "139.35",
      "month9": "7120.5",
      "month10": "11520.8",
      "month11": "186875",
      "month12": "8213.05",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-9294.617868675996",
      "pack8": "98.04297451021698",
      "pack9": "38.19439622248454",
      "pack10": "93.83502341137124",
      "pack11": "-2175.34229062285",
      "pack12": null
    },
    {
      "exciseId": "0105551039641-3-001",
      "conpanyName": "บริษัท เอ็มเอ็น ออโต้ทีม (ประเทศไทย) จำกัด",
      "address": "4 อาคาร- ซอยสุขุมวิท 62 แยก 10 ถนนสุขุมวิท แขวงบางจาก เขตพระโขนง  จังหวัดกรุงเทพมหานคร 10260",
      "subProduct": "รถยนต์",
      "sector": "ภาคที่ 1",
      "area": "ชัยนาท",
      "month1": "-",
      "month2": "-",
      "month3": "0",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "0",
      "month8": "0",
      "month9": "0",
      "month10": "1908",
      "month11": "0",
      "month12": "0",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "0.0",
      "pack8": "0.0",
      "pack9": "-100.0",
      "pack10": "100.0",
      "pack11": "0.0",
      "pack12": null
    },
    {
      "exciseId": "0107539000073-1-001",
      "conpanyName": "บริษัท เอสโซ่ (ประเทศไทย) จำกัด (มหาชน)",
      "address": "3195/17-29 ถนนพระราม 4 แขวงคลองตัน เขตคลองเตย  จังหวัดกรุงเทพมหานคร 10110",
      "subProduct": "รถยนต์",
      "sector": "ภาคที่ 1",
      "area": "นนทบุรี",
      "month1": "-",
      "month2": "-",
      "month3": "2712361462.82",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "-",
      "month8": "623772285.75",
      "month9": "2660593758.14",
      "month10": "2590649474.72",
      "month11": "2636924600.68",
      "month12": "2286351960.81",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-",
      "pack8": "76.55514736732772",
      "pack9": "-2.6998744562909165",
      "pack10": "1.754889993747519",
      "pack11": "-15.333275273409802",
      "pack12": null
    },
    {
      "exciseId": "0105527011880-1-001",
      "conpanyName": "บริษัท ศักดิ์ไชยสิทธิ จำกัด",
      "address": "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ อาคารเอ ชั้น11 ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร  จังหวัดกรุงเทพมหานคร 10900",
      "subProduct": "พรมและสิ่งทอปูพื้น",
      "sector": "ภาคที่ 1",
      "area": "สิงห์บุรี",
      "month1": "-",
      "month2": "-",
      "month3": "21762",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "56959.5",
      "month8": "27040",
      "month9": "14989",
      "month10": "41769",
      "month11": "0",
      "month12": "35399",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-110.64903846153847",
      "pack8": "-80.39895923677363",
      "pack9": "64.11453470277",
      "pack10": "100.0",
      "pack11": "-100.0",
      "pack12": null
    },
    {
      "exciseId": "0103531007512-1-001",
      "conpanyName": "ห้างหุ้นส่วนจำกัด บี.อาร์.เอส.อินเตอร์เทรด",
      "address": "660/9 อาคาร- ซอย- ถนนนครไชยศรี แขวงถนนนครไชยศรี เขตดุสิต  จังหวัดกรุงเทพมหานคร 10300",
      "subProduct": "สนามแข่งม้า",
      "sector": "ภาคที่ 1",
      "area": "ปทุมธานี 2",
      "month1": "-",
      "month2": "-",
      "month3": "6229937",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "5682380",
      "month8": "234000",
      "month9": "4572860",
      "month10": "5087910",
      "month11": "5849700",
      "month12": "5734090",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-2328.3675213675215",
      "pack8": "94.88285230687141",
      "pack9": "10.123017113117173",
      "pack10": "13.022719113800708",
      "pack11": "-2.016187398523567",
      "pack12": null
    },
    {
      "exciseId": "0100508000326-1-007",
      "conpanyName": "บริษัท เชฟรอน (ไทย) จำกัด",
      "address": "1404 อาคาร- ชั้น- หมู่ที่- ซอย- ถนนพระราม 3 แขวงช่องนนทรี เขตยานนาวา  จังหวัดกรุงเทพมหานคร 10120",
      "subProduct": "สนามกอล์ฟ",
      "sector": "ภาคที่ 1",
      "area": "ปทุมธานี 1",
      "month1": "-",
      "month2": "-",
      "month3": "20437441.18",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "18572473.55",
      "month8": "4708221.77",
      "month9": "17731109.27",
      "month10": "14649137.21",
      "month11": "17901725.4",
      "month12": "21406344.2",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-294.46896211093303",
      "pack8": "73.44654697962955",
      "pack9": "-21.038590981973595",
      "pack10": "18.16913240105894",
      "pack11": "16.371869793628754",
      "pack12": null
    },
    {
      "exciseId": "0101552450219-2-001",
      "conpanyName": "ห้างหุ้นส่วนสามัญ แสงจันทร์ เอกมัย - รามอินทรา",
      "address": "411 ซอย- ถนนประดิษฐ์มนูธรรม แขวงวังทองหลาง เขตวังทองหลาง  จังหวัดกรุงเทพมหานคร 10310",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "อยุธยา 1",
      "month1": "-",
      "month2": "-",
      "month3": "773297.31",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "803894.43",
      "month8": "571995.74",
      "month9": "1156215.04",
      "month10": "1165121.04",
      "month11": "864079.54",
      "month12": "871161.26",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-40.54203095988094",
      "pack8": "50.52860236102793",
      "pack9": "0.7643841021015293",
      "pack10": "-34.83955886746259",
      "pack11": "0.812905752948653",
      "pack12": null
    },
    {
      "exciseId": "0103505002843-2-001",
      "conpanyName": "ดี. เจ. สเตชั่น",
      "address": "8/7 ถนนสีลม 2 แขวงสุริยวงศ์ เขตบางรัก  จังหวัดกรุงเทพมหานคร 10500",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "อยุธยา 2",
      "month1": "-",
      "month2": "-",
      "month3": "624018",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "7970432.91",
      "month8": "672386",
      "month9": "500653",
      "month10": "552717",
      "month11": "670817",
      "month12": "722504",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-1085.3954291136342",
      "pack8": "-34.301801846788095",
      "pack9": "9.419648753340317",
      "pack10": "17.605397597258268",
      "pack11": "7.153870428399012",
      "pack12": null
    },
    {
      "exciseId": "0103506000062-2-001",
      "conpanyName": "ดีโน่ บาร์",
      "address": "12 ซอยสุขุมวิท 3 ถนนสุขุมวิท แขวงคลองเตยเหนือ เขตวัฒนา  จังหวัดกรุงเทพมหานคร 10110",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "ลพบุรี",
      "month1": "-",
      "month2": "-",
      "month3": "351480.38",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "577621.24",
      "month8": "5762751.24",
      "month9": "178647.62",
      "month10": "187601.86",
      "month11": "210550.47",
      "month12": "465951.4",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "89.97664108784261",
      "pack8": "-3125.764351072799",
      "pack9": "4.773001717573584",
      "pack10": "10.899339241560476",
      "pack11": "54.812783049906066",
      "pack12": null
    },
    {
      "exciseId": "0103506000470-2-001",
      "conpanyName": "เดอะรีสอร์ท",
      "address": "202/1 ชั้น 1และ ชั้น ซอยรัชดาภิเษก 13 ถนนรัชดาภิเษก แขวงดินแดง เขตดินแดง  จังหวัดกรุงเทพมหานคร 10400",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "สระบุรี",
      "month1": "-",
      "month2": "-",
      "month3": "77034.67",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "330424.3",
      "month8": "239030.84",
      "month9": "2644667.25",
      "month10": "75207.66",
      "month11": "67138.32",
      "month12": "72350.84",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-38.235007666793116",
      "pack8": "90.96178016345912",
      "pack9": "-3416.4865520347257",
      "pack10": "-12.0189781335011",
      "pack11": "7.204505158474994",
      "pack12": null
    },
    {
      "exciseId": "0103513010051-2-001",
      "conpanyName": "ร้าน นิวยอร์ค นิวยอร์ค",
      "address": "342,344,346,348 อาคาร- ชั้น- หมู่ที่- ซอย- ถนนลาดหญ้า แขวงคลองสาน เขตคลองสาน  จังหวัดกรุงเทพมหานคร 10600",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "สิงห์บุรี",
      "month1": "-",
      "month2": "-",
      "month3": "356802.8",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "80399.62",
      "month8": "85167.48",
      "month9": "1913508.35",
      "month10": "2527273.4",
      "month11": "174213.19",
      "month12": "119174.78",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "5.598216596287692",
      "pack8": "95.54914510825103",
      "pack9": "24.285660981514695",
      "pack10": "-1350.6785622833725",
      "pack11": "-46.182934006674905",
      "pack12": null
    },
    {
      "exciseId": "0103516006942-2-001",
      "conpanyName": "โรงเบียร์ลำลูกกา",
      "address": "33/3 หมู่ที่11 ตำบลบึงคำพร้อย อำเภอลำลูกกา  จังหวัดปทุมธานี 12150",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "อ่างทอง",
      "month1": "-",
      "month2": "-",
      "month3": "276519",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "277921.5",
      "month8": "336130.43",
      "month9": "316494.76",
      "month10": "442139.35",
      "month11": "2510496.05",
      "month12": "  ",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "17.317363976834823",
      "pack8": "-6.204105875244185",
      "pack9": "28.417418626050804",
      "pack10": "82.38836703208516",
      "pack11": "-",
      "pack12": null
    },
    {
      "exciseId": "0105552130790-1-001",
      "conpanyName": "บริษัท จูเคียว ยูชิ (ประเทศไทย) จำกัด",
      "address": "700/897 อาคาร- ชั้น- หมู่ที่3 ซอย- ถนน- ตำบลหนองกะขะ อำเภอพานทอง  จังหวัดชลบุรี 20160",
      "subProduct": "ไพ่",
      "sector": "ภาคที่ 1",
      "area": "ปทุมธานี 1",
      "month1": "-",
      "month2": "-",
      "month3": "19370",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "0",
      "month8": "12915",
      "month9": "0",
      "month10": "12915",
      "month11": "25830",
      "month12": "0",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-100.0",
      "pack8": "100.0",
      "pack9": "-100.0",
      "pack10": "50.0",
      "pack11": "100.0",
      "pack12": null
    },
    {
      "exciseId": "0100515042462-1-004",
      "conpanyName": "บริษัท เชลล์แห่งประเทศไทย จำกัด",
      "address": "10 อาคารเชลล์ ถนนสุนทรโกษา แขวงคลองเตย เขตคลองเตย  จังหวัดกรุงเทพมหานคร 10110",
      "subProduct": "ไพ่",
      "sector": "ภาคที่ 1",
      "area": "ชัยนาท",
      "month1": "-",
      "month2": "-",
      "month3": "8346885.3",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "8293455.82",
      "month8": "2256257.5",
      "month9": "6341272.65",
      "month10": "6745752",
      "month11": "8108517.51",
      "month12": "8136753.3",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-267.57576739357097",
      "pack8": "64.41948447051871",
      "pack9": "5.996060187211146",
      "pack10": "16.806592676396647",
      "pack11": "0.34701543673445323",
      "pack12": null
    },
    {
      "exciseId": "0107536000064-1-001",
      "conpanyName": "บริษัท ซัสโก้ จำกัด (มหาชน)",
      "address": "139 ถนนราษฎร์บูรณะ แขวงบางปะกอก เขตราษฎร์บูรณะ  จังหวัดกรุงเทพมหานคร 10140",
      "subProduct": "แก้วและเครื่องแก้ว",
      "sector": "ภาคที่ 1",
      "area": "นนทบุรี",
      "month1": "-",
      "month2": "-",
      "month3": "669842.55",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "255387.6",
      "month8": "274768.65",
      "month9": "535081.95",
      "month10": "705451.5",
      "month11": "846822.6",
      "month12": "387983.7",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "7.053588537120234",
      "pack8": "48.64923961647369",
      "pack9": "24.15042706692098",
      "pack10": "16.69429937273757",
      "pack11": "-118.26241669430956",
      "pack12": null
    },
    {
      "exciseId": "0107536000269-1-001",
      "conpanyName": "บริษัท บางจาก คอร์ปอเรชั่น จำกัด (มหาชน)",
      "address": "2098 อาคารเอ็ม ทาวเวอร์ ชั้น8 ถนนสุขุมวิท แขวงพระโขนงใต้ เขตพระโขนง  จังหวัดกรุงเทพมหานคร 10260",
      "subProduct": "แก้วและเครื่องแก้ว",
      "sector": "ภาคที่ 1",
      "area": "ปทุมธานี 1",
      "month1": "-",
      "month2": "-",
      "month3": "5143922.55",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "6281074.8",
      "month8": "1637262.88",
      "month9": "5649713.55",
      "month10": "6203340",
      "month11": "6109137.45",
      "month12": "5709956.85",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-283.63263937187656",
      "pack8": "71.02042669048238",
      "pack9": "8.924651075066016",
      "pack10": "-1.5419942794051853",
      "pack11": "-6.990956507841221",
      "pack12": null
    },
    {
      "exciseId": "0107544000108-1-013",
      "conpanyName": "บริษัท ไวตามิลด์ จำกัด)",
      "address": "555 หมู่ที่1 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร  จังหวัดกรุงเทพมหานคร 10900",
      "subProduct": "เครื่องดื่ม",
      "sector": "ภาคที่ 1",
      "area": "ลพบุรี",
      "month1": "-",
      "month2": "-",
      "month3": "21457603.05",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "19715125.3",
      "month8": "5926890.45",
      "month9": "19496619.35",
      "month10": "20081204",
      "month11": "19014498.75",
      "month12": "18289133.85",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-232.63859803583853",
      "pack8": "69.60041972609984",
      "pack9": "2.9111035872151816",
      "pack10": "-5.609957243811121",
      "pack11": "-3.966097607186567",
      "pack12": null
    },
    {
      "exciseId": "0105495000551-1-002",
      "conpanyName": "บริษัท น้ำมันปิโตรเลียมไทย จำกัด",
      "address": "1 อาคารที พี แอนด์ ที ชั้น9 - 10 ซอยวิภาวดีรังสิต 19 ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร  จังหวัดกรุงเทพมหานคร 10900",
      "subProduct": "น้ำมัน",
      "sector": "ภาคที่ 1",
      "area": "สิงห์บุรี",
      "month1": "-",
      "month2": "-",
      "month3": "13880330",
      "month4": "-",
      "month5": "-",
      "month6": "-",
      "month7": "9485892.15",
      "month8": "1559585",
      "month9": "12718485",
      "month10": "11546042.25",
      "month11": "13732770",
      "month12": "8686170",
      "pack1": "-",
      "pack2": "-",
      "pack3": "-",
      "pack4": "-",
      "pack5": "-",
      "pack6": "-",
      "pack7": "-508.2318148738286",
      "pack8": "87.73765114319826",
      "pack9": "-10.154499044899996",
      "pack10": "15.92342804838354",
      "pack11": "-58.09925433188621",
      "pack12": null
    },
  ];
  dataTableCompare2Year: any = [
    {
      "year": "2560",
      "exciseId": "0775546000001-1-001",
      "conpanyName": "บริษัท เนเจอร์รี่ จำกัด",
      "address": "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ ชั้น11 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
      "subProduct": "เครื่องดื่ม",
      "sector": "ภาคที่ 2",
      "area": "ชลบุรี 1",
      "month1": "19014498.75",
      "month2": "18289133.85",
      "month3": "20482213.70",
      "month4": "22445482.80",
      "month5": "-",
      "month6": "-",
      "month7": "-",
      "month8": "19813300",
      "month9": "20442213.70",
      "month10": "19289133.85",
      "month11": "19989133.85",
      "month12": "-",
      "showCh": "show"
    }, {
      "year": "2561",
      "exciseId": "0775546000001-1-001",
      "conpanyName": "บริษัท เนเจอร์รี่ จำกัด",
      "address": "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ ชั้น11 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
      "subProduct": "เครื่องดื่ม",
      "sector": "ภาคที่ 2",
      "area": "ชลบุรี 1",
      "month1": "20482213.70",
      "month2": "20482213.70",
      "month3": "18289133.85",
      "month4": "-",
      "month5": "-",
      "month6": "19514498.75",
      "month7": "-",
      "month8": "-",
      "month9": "-",
      "month10": "-",
      "month11": "-",
      "month12": "-",
      "showCh": ""
    },
    {
      "year": "",
      "exciseId": "",
      "conpanyName": "",
      "address": "",
      "subProduct": "",
      "sector": "",
      "area": "",
      "month1": "-1467714.95",
      "month2": "-2193079.85",
      "month3": "2193079.85",
      "month4": "22445482.80",
      "month5": "-",
      "month6": "-19514498.75",
      "month7": "-",
      "month8": "-",
      "month9": "20442213.70",
      "month10": "19289133.85",
      "month11": "19989133.85",
      "month12": "-",
      "showCh": ""
    },
    {
      "year": "2560",
      "exciseId": "0105540039831-3-001",
      "conpanyName": "บริษัท ดัชมิลด์ ดิไวซ์ เซลส์ (ประเทศไทย) จำกัด",
      "address": "252/133 อาคาร- ซอย- ถนนรัชดาภิเษก แขวงห้วยขวาง เขตห้วยขวาง  จังหวัดกรุงเทพมหานคร 10310",
      "subProduct": "เครื่องดื่ม",
      "sector": "สรรพสามิตภาคที่ 1",
      "area": "สรรพสามิตพื้นที่ปทุมธานี 2",
      "month1": "19014498.75",
      "month2": "18289133.85",
      "month3": "20482213.70",
      "month4": "22445482.80",
      "month5": "-",
      "month6": "-",
      "month7": "-",
      "month8": "-",
      "month9": "20442213.70",
      "month10": "19289133.85",
      "month11": "19989133.85",
      "month12": "-",
      "showCh": "show"
    }, {

      "year": "2561",
      "exciseId": "0105540039831-3-001",
      "conpanyName": "บริษัท ดัชมิลด์ ดิไวซ์ เซลส์ (ประเทศไทย) จำกัด",
      "address": "252/133 อาคาร- ซอย- ถนนรัชดาภิเษก แขวงห้วยขวาง เขตห้วยขวาง  จังหวัดกรุงเทพมหานคร 10310",
      "subProduct": "เครื่องดื่ม",
      "sector": "สรรพสามิตภาคที่ 1",
      "area": "สรรพสามิตพื้นที่ปทุมธานี 2",
      "month1": "20482213.7",
      "month2": "20482213.7",
      "month3": "20482213.70",
      "month4": "22445482.80",
      "month5": "-",
      "month6": "-",
      "month7": "-",
      "month8": "-",
      "month9": "20442213.70",
      "month10": "19289133.85",
      "month11": "19989133.85",
      "month12": "-",
      "showCh": ""
    }, {

      "year":"",
      "exciseId":"",
      "conpanyName": "",
      "address": "",
      "subProduct": "",
      "sector": "",
      "area": "",
      "month1": "-1467714.95",
      "month2": "-2193079.85",
      "month3": "2193079.85",
      "month4": "0.00",
      "month5": "-",
      "month6": "-",
      "month7": "-",
      "month8": "-",
      "month9": "0",
      "month10": "0",
      "month11": "0",
      "month12": "-",
      "showCh": ""
    }
  ];
  constructor(
    private ajax: AjaxService,
    private router: Router
  ) { }

  checkRisk(risk) {
    if (risk == this.risk.riskCode1) risk = this.risk.riskDesc1;
    if (risk == this.risk.riskCode2) risk = this.risk.riskDesc2;
    if (risk == this.risk.riskCode3) risk = this.risk.riskDesc3;
    return risk;
  }
  save(): Promise<any> {
    let url = "taxAudit/selectList/saveCondition1";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify({ dataList: this.dataList }), res => {
        this.router.navigate(['/tax-audit-select-line/tsl0102-00']);
        resolve(res.json());
      });
    });
  }

  activeTable(e) {
    if (e == 1) {
      // this.table.clear().draw();
      // this.table.rows.add(this.data); // Add new data
      // this.table.columns.adjust().draw(); // Redraw the DataTable\
    }
    if (e == 2) {
      // this.tableC.clear().draw();
      // this.tableC.rows.add(this.dataTableCompare); // Add new data
      // this.tableC.columns.adjust().draw(); // Redraw the DataTable\
    }
    if (e == 3) {
      // this.tableC2y.clear().draw();
      // this.tableC2y.rows.add(this.dataTableCompare2Year); // Add new data
      // this.tableC2y.columns.adjust().draw(); // Redraw the DataTable\
    }
  }
  datatable = () => {
    this.table = $("#datatable1").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.data,
      //   fixedColumns:   {
      //     leftColumns: 3,
      // },
      "columns": [
        {
          render: function (data, type, full, meta) {
            return '<div class="ui checkbox tableDt"><input name="checkDelIddatatable1" id="checkDelIddatatable1" value="' +
              data +
              '" type="checkbox"><label></label></div>';
          },
          className: "center"
        },
        {
          "data": "exciseId"
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "subProduct"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        },
        {
          "data": "month1",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month2",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month3",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month4",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month5",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month6",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month7",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month8",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month9",
          "className": "text-right"
        },
        {
          "data": "month10",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month11",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month12",
          "className": "text-right"
          ,
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
      ],
      "rowCallback": (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let d = {
            address: data.address,
            area: data.area,
            conpanyName: data.conpanyName,
            exciseId: data.exciseId,
            sector: data.sector,
            subProduct: data.subProduct,
            riskType: "1"
          }
          let isDelete = false;
          for (let index = 0; index < this.dataList.length; index++) {
            const element = this.dataList[index];
            if (JSON.stringify(element) == JSON.stringify(d)) {
              isDelete = true;

              this.dataList.splice(index, 1);

            }
          }
          if (!isDelete) {
            this.dataList.push(d);
          }
          console.log(this.dataList);
          this.table2.clear().draw();
          this.table2.rows.add(this.dataList); // Add new data
          this.table2.columns.adjust().draw(); // Redraw the DataTable\
        })

      }


    });
    $("#select-all").on("click", function () {

      // Check/uncheck all checkboxes in the table
      var rows = $("#datatable1").DataTable().rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);

      var allVals = [];
      $('#checkDelIddatatable1 :checked').each(function () {
        allVals.push($(this).val());
      });
      //$('#t').val(allVals);
      console.log(allVals);
    });
  }
  // table compare
  tableCompare = () => {
    this.tableC = $("#tableCompare").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.dataTableCompare,
      "columns": [
        {
          render: function (data, type, full, meta) {
            return '<div class="ui checkbox tableDt"><input id="checkDelIdtableCompare" name="checkDelIdtableCompare" value="' +
              data +
              '" type="checkbox"><label></label></div>';
          },
          "className": "center"
        },
        {
          "data": "exciseId",
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "subProduct"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        },
        {
          "data": "month1",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month2",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month3",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month4",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month5",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month6",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month7",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month8",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month9",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month10",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month11",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month12",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack1",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack2",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack3",
          "className": "right",
          "render": (data, row) => {
            console.log(data);
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack4",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack5",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack6",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack7",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack8",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack9",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack10",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "pack11",
          "className": "right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }
      ],
      "rowCallback": (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let d = {
            address: data.address,
            area: data.area,
            conpanyName: data.conpanyName,
            exciseId: data.exciseId,
            sector: data.sector,
            subProduct: data.subProduct,
            riskType: "2"
          }
          let isDelete = false;
          for (let index = 0; index < this.dataList.length; index++) {
            const element = this.dataList[index];
            if (JSON.stringify(element) == JSON.stringify(d)) {
              isDelete = true;

              this.dataList.splice(index, 1);

            }
          }
          if (!isDelete) {
            this.dataList.push(d);
          }
          this.table2.clear().draw();
          this.table2.rows.add(this.dataList); // Add new data
          this.table2.columns.adjust().draw(); // Redraw the DataTable\
        })

      }


    });
    $("#select-all").on("click", function () {

      // Check/uncheck all checkboxes in the table
      var rows = $("#datatable1").DataTable().rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  tableCompare2Year = () => {
    this.tableC2y = $("#tableCompare2Year").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.dataTableCompare2Year,
      "columns": [
        {
          render: function (data, row, type, full, meta) {
            if (Utils.isNotNull(type.showCh)) {
              return '<div class="ui checkbox tableDt"><input id="checkDelIdtableC2y" name="checkDelIdtableC2y" value="' +
                data +
                '" type="checkbox"><label></label></div>';
            }
            return '';
          },
          className: "center"
        },
        {
          "data": "year"
        }, {
          "data": "exciseId"
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "subProduct"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        },
        {
          "data": "month1",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month2",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month3",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month4",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month5",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month6",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month7",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month8",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month9",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
        {
          "data": "month10",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month11",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        }, {
          "data": "month12",
          "className": "text-right",
          "render": (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
      ],
      "rowCallback": (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let d = {
            address: data.address,
            area: data.area,
            conpanyName: data.conpanyName,
            exciseId: data.exciseId,
            sector: data.sector,
            subProduct: data.subProduct,
            riskType: "3"
          }
          let isDelete = false;
          for (let index = 0; index < this.dataList.length; index++) {
            const element = this.dataList[index];
            if (JSON.stringify(element) == JSON.stringify(d)) {
              isDelete = true;

              this.dataList.splice(index, 1);

            }
          }
          if (!isDelete) {
            this.dataList.push(d);
          }
          this.table2.clear().draw();
          this.table2.rows.add(this.dataList); // Add new data
          this.table2.columns.adjust().draw(); // Redraw the DataTable\
        })

      }


    });
    $("#select-all").on("click", function () {
      // Check/uncheck all checkboxes in the table
      var rows = $("#datatable1").DataTable().rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }
  datatable2 = () => {
    this.table2 = $("#datatable2").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.dataList,
      "columns": [
        {
          "data": "exciseId"
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        }, {
          "data": "subProduct"
        }, {
          "data": "riskType",
          "render": (data, row) => {
            return this.checkRisk(data);
          }
        }

      ],
    });
  }

  dataTaxNonpay(form): Promise<any> {
    let url = "taxAudit/selectList/findCondition1";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(form), (res) => {
        this.data = res.json();
        resolve(res.json());
      })
    });
  }

  dataTableCompareList(form): Promise<any> {
    let url = "taxAudit/selectList/findCondition2";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(form), (res) => {
        this.dataTableCompare = res.json();
        resolve(res.json());
      })
    });
  }

  select = () => {
    var data = $("#datatable1").DataTable().rows().data();
    for (let i = 0; i < data.length; i++) {
      if ((<HTMLInputElement>document.getElementById(`chk${i}`)).checked) {
        this.dataSelect.push(data[i]);
      }
    } //end for loops
  }
}


