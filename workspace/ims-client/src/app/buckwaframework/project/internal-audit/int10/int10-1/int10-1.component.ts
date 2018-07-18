import { Component, OnInit } from '@angular/core';
import { TextDateTH } from '../../../../common/helper';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int10-1',
  templateUrl: './int10-1.component.html',
  styleUrls: ['./int10-1.component.css']
})
export class Int101Component implements OnInit {

  a : number =0;
  b : number =0;
  c : number =0;
  constructor() { }

  ngOnInit() {
    $("#calendar").calendar({
      maxDate: new Date(),
      type: "month",
      text: TextDateTH
    });
    $("#calendar1").calendar({
      maxDate: new Date(),
      type: "month",
      text: TextDateTH
    });

    
  }

}
