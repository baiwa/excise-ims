import { Component, OnInit } from '@angular/core';
import { TextDateTH } from '../../../../../common/helper';
declare var $: any;
@Component({
  selector: 'ope04-4',
  templateUrl: './ope04-4.component.html',
  styleUrls: ['./ope04-4.component.css']
})
export class Ope044Component implements OnInit {
  
  public showData: boolean = false;

  constructor() { }

  ngOnInit() {

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ope04-1").css("width", "100%");

    $("#calendarFront").calendar({
      endCalendar: $("#calendarLast"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      
      
    });

    $("#calendarLast").calendar({
      startCalendar: $("#calendarFront"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
    
    });
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}
