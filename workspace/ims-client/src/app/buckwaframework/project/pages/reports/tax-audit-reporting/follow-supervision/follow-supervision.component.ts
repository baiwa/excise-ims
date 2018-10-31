import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { ActivatedRoute } from '@angular/router';
import { AjaxService } from 'services/ajax.service';
declare var $: any;
@Component({
  selector: 'app-follow-supervision',
  templateUrl: './follow-supervision.component.html',
  styleUrls: ['./follow-supervision.component.css']
})
export class FollowSupervisionComponent implements OnInit {
  @Output() discard = new EventEmitter<any>();
  obj: followSupervision;
  
  constructor(    private route: ActivatedRoute,
    private ajax: AjaxService) {
      this.obj = new followSupervision();

     }


  ngOnInit() {
    this.calenda();

  }

  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date1"),
      type: "date",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
   
  }
  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };
}
class followSupervision{
  governmentService : string;
  date : string;   
  subject : string;
  study : string;
  actionMonitoring : string;
  resultsAre : string;
  fromOperation : string;
}
