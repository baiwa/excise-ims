import { Component, OnInit } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int05-1-1-1',
  templateUrl: './int05-1-1-1.component.html',
  styleUrls: ['./int05-1-1-1.component.css']
})
export class Int05111Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#date-receive-pay').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-withdraw').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-check').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-send').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });
  }

}
