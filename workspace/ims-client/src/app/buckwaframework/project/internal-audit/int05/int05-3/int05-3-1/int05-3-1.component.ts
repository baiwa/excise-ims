import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../../common/services/ajax.service';

@Component({
  selector: 'int05-3-1',
  templateUrl: './int05-3-1.component.html',
  styleUrls: ['./int05-3-1.component.css']
})
export class Int0531Component implements OnInit {

  constructor(private ajax: AjaxService) { }

  ngOnInit() {
  }

  callFn() {
    this.ajax.get(
      'api/preferences/message',
      alert('success\n** Just alert..!'),
      ret => console.log(ret)
    ).then(
      res => console.log(res.json().data),
      error => console.log(error)
    );
    // this.ajax.post(
    //   'api/post/me',
    //   {
    //     startBackDate: '01/2539',
    //     month: '10'
    //   },
    //   alert('success')
    // ).then(
    //   res => console.log(res),
    //   err => console.log(err)
    // );
  }
}