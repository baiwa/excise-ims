import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-analyst-basic-data-trader',
  templateUrl: './analyst-basic-data-trader.component.html',
  styleUrls: []
})
export class AnalystBasicDataTraderComponent implements OnInit {
  listMenu:any[] = [];
  showmenu:boolean = true;
  constructor() { }

  ngOnInit() {
    this.listMenu = ["น้ำมัน"	
      , "เครื่องดื่ม"	
      , "ยาสูบ"	
      , "ไพ่"	
      , "แก้วและเครื่องแก้ว"	
     , "รถยนต์"	
     , 'พรมและสิ่งทอปูพื้น'	
     , "แบตเตอรี่"	
     , "ไนท์คลับและดิสโกเธค"	
     , "สถานอาบน้ำหรืออบตัวและนวด"	
     , "สนามแข่งม้า"	
     , 'สนามกอล์ฟ'	
     , "รวม"]	;
  }

}
