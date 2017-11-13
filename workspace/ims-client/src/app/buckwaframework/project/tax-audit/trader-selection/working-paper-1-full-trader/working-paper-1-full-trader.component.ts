import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-working-paper-1-full-trader',
  templateUrl: './working-paper-1-full-trader.component.html',
  styleUrls: ['./working-paper-1-full-trader.component.css']
})
export class WorkingPaper1FullTraderComponent implements OnInit {

  private listItem: any[];

  constructor() { }

  ngOnInit() {
    this.listItem = ["น้ำมัน"
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
      , "รวม"];
  }

}
