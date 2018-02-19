import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int07-5-2',
  templateUrl: './int07-5-2.component.html',
  styleUrls: ['./int07-5-2.component.css']
})
export class Int0752Component implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }
}
