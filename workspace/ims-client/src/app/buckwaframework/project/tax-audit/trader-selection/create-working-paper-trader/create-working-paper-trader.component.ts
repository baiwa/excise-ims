import { Component, OnInit } from '@angular/core';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
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
  constructor(private messageBarService: MessageBarService, private route: ActivatedRoute, private router: Router) {
    this.numbers = [1];

    this.data = [];
    this.num1 = [];
    this.num2 = [];
    this.percent1 = [];
    this.percent2 = [];
    for (let i = 0; i < 10; i++) {
      this.data.push(`A${i}`);
      this.num1.push(0);
      this.num2.push(0);
      this.percent1.push(0);
      this.percent2.push(0);
    }
  }
  before: any;
  last: any;
  num_month: any;
  // num1: any;
  // num2: any;
  num1: number[];
  num2: number[];
  percent1: number[];
  percent2: number[];
  data: string[];

  ngOnInit() {
    // subscribe to router event
    this.before = this.route.snapshot.queryParams["before"];
    this.last = this.route.snapshot.queryParams["last"];
    this.num_month = this.route.snapshot.queryParams["num_month"];
    console.log(this.num_month)
  }

  onSend = () => {
    this.messageBarService.successModal('สร้างกระดาษทำการเรียบร้อยแล้ว', 'สำเร็จ');
    this.router.navigate(
      ['/working-paper-1-trader']
    );
  }

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 10) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal('ไม่สามารถทำรายการได้', 'เกิดข้อผิดพลาด');
    }
  };

  onDelField = index => {
    this.numbers.splice(index, 1);
  };
}
