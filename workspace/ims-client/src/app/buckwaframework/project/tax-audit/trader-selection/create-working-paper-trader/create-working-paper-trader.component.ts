import { Component, OnInit } from '@angular/core';
import { MessageBarService } from '../../../../common/services/message-bar.service';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-create-working-paper-trader',
  templateUrl: './create-working-paper-trader.component.html',
  styleUrls: []
})
export class CreateWorkingPaperTraderComponent implements OnInit {

  private count: number = 1;
  constructor(
    private messageBarService: MessageBarService
  ) {

  }



  ngOnInit() {

  }

  addCondition() {
    if (this.count == 10) {
      this.messageBarService.error("ไม่สามารถทำรายการได้");

      return;
    }

    $("#addCondition").append(

      '<div class="inline fields">                   ' +
      '	<div class="three wide field ">            ' +
      '		<label>กำหนดเงื่อนไข</label></div>          ' +
      '  <div class="three wide field ">             ' +
      '	<label>จำนวนเดือนที่ชำระภาษี</label>                ' +
      '  </div>                                      ' +
      '  <div class="two wide field ">               ' +
      '	<input name="" value="">                   ' +
      '  </div>                                      ' +
      '  <div class="one wide field ">               ' +
      '	<label>&nbsp;&nbsp;&nbsp;ถึง&nbsp;</label>  ' +
      '  </div>                                      ' +
      '  <div class="two wide field ">               ' +
      '	<input name="" value="">                   ' +
      '  </div>                                      ' +
      '  <div class="five wide field ">              ' +
      '	<label>ช่วงร้อยละที่ต้องการแยกกลุ่มข้อมูล</label>          ' +
      '  </div>                                      ' +
      '  <div class="two wide field ">               ' +
      '	<input name="" value="">                   ' +
      '  </div>                                      ' +
      '  <div class="one wide field ">               ' +
      '	<label>&nbsp;&nbsp;&nbsp;ถึง&nbsp;</label>  ' +
      '  </div>                                      ' +
      '  <div class="two wide field ">               ' +
      '	<input name="" value="">                   ' +
      '  </div>                                      ' +
      '</div>                                        '


    );
    this.count++;
  }
}
