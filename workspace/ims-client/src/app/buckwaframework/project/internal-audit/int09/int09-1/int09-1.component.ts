
import { Component, OnInit } from '@angular/core';
import { TextDateTH } from '../../../../common/helper/datepicker';
import { AjaxService } from '../../../../common/services';
declare var $: any;
@Component({
  selector: 'app-int09-1',
  templateUrl: './int09-1.component.html',
  styleUrls: ['./int09-1.component.css']
})
export class Int091Component implements OnInit {
  private $form: any;
  private showData: boolean = false;
  constructor() { }

  ngOnInit() {
    $('#example2').calendar({
      type: 'date',
      text: TextDateTH
    });
    $('#example3').calendar({
      type: 'date',
      text: TextDateTH
    });
    this.$form = $('#int09-1');
  }


  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
  onSubmit = () => {
    let allFields = this.$form.form('get values');
    console.log("allFields", allFields);

    const URL = AjaxService.CONTEXT_PATH + "/a/b/c";
    console.log(URL);
    var parameter = {};

    $.post(URL,
      function (data) {
        console.log(data);
      }).fail(function () {

        console.log("error");
      });
  };

  saveT

}

