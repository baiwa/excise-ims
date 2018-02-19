import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-int07-5-4',
  templateUrl: './int07-5-4.component.html',
  styleUrls: ['./int07-5-4.component.css']
})
export class Int0754Component implements OnInit {

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
