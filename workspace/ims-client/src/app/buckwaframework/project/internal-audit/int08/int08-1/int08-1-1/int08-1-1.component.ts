import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int08-1-1',
  templateUrl: './int08-1-1.component.html',
  styleUrls: ['./int08-1-1.component.css']
})
export class Int0811Component implements OnInit {

  private showData: boolean = false;
  public data: String[];

  constructor() {
    this.data =  [
      "ประเมินความเสี่ยงโครงการ - งบประมาณ",
      "ประเมินความเสี่ยงโครงการ - ประสิทธิภาพ",
      "ประเมินความเสี่ยงโครงการ - รวม",
      "ประเมินความเสี่ยงสารสนเทศ - จำนวนครั้งใช้งานไม่ได้",
      "ประเมินความเสี่ยงสารสนเทศ - ร้อยละความเห็น",
      "ประเมินความเสี่ยงภาคพื้นที่ - ความถี่การตรวจ",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการจัดเก็บรายได้",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (ค่าปรับ)",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (คดี)",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (รวม)",
      "ประเมินความเสี่ยงภาคพื้นที่ - การเงินและบัญชี",
      "ประเมินความเสี่ยงภาคพื้นที่ - ควบคุมภายใน",
      "ประเมินความเสี่ยงภาคพื้นที่ - รวม",
    ];
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#selectCondition1').dropdown();
    $('#selectC1').dropdown();
    $('#selectCondition2').dropdown();
    $('#selectCondition3').dropdown();
    $('#selectColor1').dropdown();
    $('#selectColor2').dropdown();
    $('#selectColor3').dropdown();
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }


  popupEditData() {
    $('#select1').show();
    $('#select2').show();
    $('#select3').show();
    $('#modalInt0811').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt0811').modal('hide');
  }
}
