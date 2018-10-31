import { Component, OnInit, Output,EventEmitter } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';

declare var $: any;
@Component({
  selector: 'app-get-app-roved',
  templateUrl: './get-app-roved.component.html',
  styleUrls: ['./get-app-roved.component.css']
})
export class GetAppRovedComponent implements OnInit {
  @Output() discard = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
    this.calenda();
  }
  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };
  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date1"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
    $("#date2").calendar({
      startCalendar: $("#date1"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
    $("#date2").calendar({
      startCalendar: $("#date3"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
  }


  governmentService : string;
  date : string;   
  subject : string;
  study : string;
  travelToWork : string;
  travelToWork1 : string;
  travelToWork2 : string;
  travelToWork3 : string;
  travelToWork4 : string;
  travelToWork5 : string;
  accordingToPlan : string;
  accordingToPlan1 : string;
  companyAccount1 : string;
  companyAccount2 : string;
  companyAccount3 : string;
  companyAccount4 : string;
  companyAccount5 : string;
}
