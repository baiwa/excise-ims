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
  numbers: number[];
  constructor(private messageBarService: MessageBarService) {
    this.numbers = [1];
  }

  ngOnInit() {
  }

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 10 ) {
      this.numbers.push(num+1);
    } else {
      this.messageBarService.errorModal('ไม่สามารถทำรายการได้', 'เกิดข้อผิดพลาด');
    }
  };
  
  onDelField = index => {
    this.numbers.splice(index, 1);
  };
}
