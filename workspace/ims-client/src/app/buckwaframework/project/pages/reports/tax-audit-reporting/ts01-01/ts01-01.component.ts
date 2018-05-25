import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageBarService } from '../../../../../common/services/message-bar.service';

@Component({
  selector: 'app-ts01-01',
  templateUrl: './ts01-01.component.html',
  styleUrls: ['./ts01-01.component.css']
})
export class Ts0101Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  numbers:number[];

  constructor(private messageBarService: MessageBarService) {
    this.numbers = [1,2,3];
  }

  ngOnInit() {
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num+1);
    } else {
      this.messageBarService.errorModal('ไม่สามารถทำรายการได้', 'เกิดข้อผิดพลาด');
    }
  };
  
  onDelField = index => {
    this.numbers.splice(index, 1);
  };
}
