import { Component, AfterViewInit } from "@angular/core";

declare var $: any;

@Component({
    selector: 'ui-forms',
    templateUrl: './forms.html',
    styleUrls: ['./forms.css']
})
export class Forms implements AfterViewInit{

    constructor() {
        // TODO
    }

    ngAfterViewInit() {
        $('.ui.dropdown.selection').dropdown().css('width', '100%');
    }

    onSubmit(e: any): void {
        e.preventDefault();
    }

    async copyForm(e: any, what:string) {
        e.target.innerHTML = await "copied...";
        let $temp = await $("<textarea id='copyToClipboard'></textarea>");
        await $("body").append($temp);
        await $temp.val(this.selectForm(what)).select();
        await document.execCommand("copy");
        await $temp.remove();
        setTimeout(() => e.target.innerHTML = "copied...", 200);
        setTimeout(() => e.target.innerHTML = "copied..", 400);
        setTimeout(() => e.target.innerHTML = "copied.", 800);
        setTimeout(() => e.target.innerHTML = "copy", 1000);
    }

    selectForm(what: string) {
        switch (what) {
            case 'inline-input':
                return `<form class="ui form" (submit)="func($event)">\r\n\t<div class="inline fields">\r\n\r\n\t\t<!-- Label and Input -->\r\n\t\t<div class="two wide field">\r\n\t\t\t<label class="text-right full-width"> Text</label>\r\n\t\t</div><div class="five wide field">\r\n\t\t\t<div class="ui input">\r\n\t\t\t\t<input type="text" name="name">\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<!-- Label and Input -->\r\n\r\n\t\t<!-- Label and Input -->\r\n\t\t<div class="two wide field">\r\n\t\t\t<label class="text-right full-width"> Text</label></form>\r\n\t\t</div>\r\n\t\t<div class="five wide field">\r\n\t\t\t<div class="ui input">\r\n\t\t\t\t<input type="text" name="name">\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<!-- Label and Input -->\r\n\r\n\t\t<div class="two wide field">\r\n\t\t\t<button class="ui button" type="submit">Submit</button>\r\n\t\t</div>\r\n\t</div>\r\n</form>`;
            case 'inline-dropdown':
                return `<form class="ui form" (submit)="func($event)">\r\n\t<div class="inline fields">\r\n\r\n\t\t<!-- Label and Input -->\r\n\t\t<div class="two wide field">\r\n\t\t\t<label class="text-right full-width"> Text</label>\r\n\t\t</div><div class="five wide field">\r\n\t\t\t<div class="ui input">\r\n\t\t\t\t<select class="ui search dropdown selection">\r\n\t\t\t\t\t<option value="">Select</option>\r\n\t\t\t\t\t<option value="Value">Select Label</option>\r\n\t\t\t\t</select>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<!-- Label and Input -->\r\n\r\n\t\t<!-- Label and Input -->\r\n\t\t<div class="three wide field">\r\n\t\t\t<label class="text-right full-width"> Text</label>\r\n\t\t</div>\r\n\t\t<div class="five wide field">\r\n\t\t\t<div class="ui input">\r\n\t\t\t\t<select class="ui dropdown selection">\r\n\t\t\t\t\t<option value="">Select</option>\r\n\t\t\t\t\t<option value="Value">Select Label</option>\r\n\t\t\t\t</select>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<!-- Label and Input -->\r\n\r\n\t</div>\r\n</form>`;
        }
    }
}