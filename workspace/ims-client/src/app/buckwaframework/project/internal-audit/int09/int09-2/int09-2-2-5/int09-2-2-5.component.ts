import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-2-5',
  templateUrl: './int09-2-2-5.component.html'
})
export class Int09225Component implements OnInit {
  form: FormGroup;
  constructor() {}


  onFileChange(event) {
    if(event.target.files.length > 0) {
      let file = event.target.files[0];
      this.form.get('avatar').setValue(file);
    }
  }

  ngOnInit() {
  }

}
