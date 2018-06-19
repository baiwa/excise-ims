import { Component, OnInit } from '@angular/core';
import { formatter, TextDateTH } from '../../../../common/helper/datepicker';
declare var $: any;
@Component({
  selector: 'app-mgc01-1',
  templateUrl: './mgc01-1.component.html',
  styleUrls: ['./mgc01-1.component.css']
})
export class Mgc011Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('.ui.radio.checkbox').checkbox();
    $('#example4').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter()
    });
  }

}
