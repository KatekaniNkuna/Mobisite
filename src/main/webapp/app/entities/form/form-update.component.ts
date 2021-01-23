import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IForm, Form } from 'app/shared/model/form.model';
import { FormService } from './form.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-form-update',
  templateUrl: './form-update.component.html',
})
export class FormUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    competitor: [null, [Validators.required]],
    province: [],
    image: [],
    imageContentType: [],
    offerDate: [null, [Validators.required]],
    productType: [null, [Validators.required]],
    typeOfOffer: [null, [Validators.required]],
    offerType: [null, [Validators.required]],
    offerPurchaseLimitation: [null, [Validators.required]],
    natureOFOffer: [null, [Validators.required]],
    offerPrice: [null, [Validators.required, Validators.min(0)]],
    offerValidity: [null, [Validators.required]],
    offerAllocation: [null, [Validators.required, Validators.min(0)]],
    offerEndDate: [null, [Validators.required]],
    riskOfMarketShareLoss: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected formService: FormService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ form }) => {
      if (!form.id) {
        const today = moment().startOf('day');
        form.offerDate = today;
        form.offerEndDate = today;
      }

      this.updateForm(form);
    });
  }

  updateForm(form: IForm): void {
    this.editForm.patchValue({
      id: form.id,
      competitor: form.competitor,
      province: form.province,
      image: form.image,
      imageContentType: form.imageContentType,
      offerDate: form.offerDate ? form.offerDate.format(DATE_TIME_FORMAT) : null,
      productType: form.productType,
      typeOfOffer: form.typeOfOffer,
      offerType: form.offerType,
      offerPurchaseLimitation: form.offerPurchaseLimitation,
      natureOFOffer: form.natureOFOffer,
      offerPrice: form.offerPrice,
      offerValidity: form.offerValidity,
      offerAllocation: form.offerAllocation,
      offerEndDate: form.offerEndDate ? form.offerEndDate.format(DATE_TIME_FORMAT) : null,
      riskOfMarketShareLoss: form.riskOfMarketShareLoss,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('mobisiteApp.error', { message: err.message })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const form = this.createFromForm();
    if (form.id !== undefined) {
      this.subscribeToSaveResponse(this.formService.update(form));
    } else {
      this.subscribeToSaveResponse(this.formService.create(form));
    }
  }

  private createFromForm(): IForm {
    return {
      ...new Form(),
      id: this.editForm.get(['id'])!.value,
      competitor: this.editForm.get(['competitor'])!.value,
      province: this.editForm.get(['province'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      offerDate: this.editForm.get(['offerDate'])!.value ? moment(this.editForm.get(['offerDate'])!.value, DATE_TIME_FORMAT) : undefined,
      productType: this.editForm.get(['productType'])!.value,
      typeOfOffer: this.editForm.get(['typeOfOffer'])!.value,
      offerType: this.editForm.get(['offerType'])!.value,
      offerPurchaseLimitation: this.editForm.get(['offerPurchaseLimitation'])!.value,
      natureOFOffer: this.editForm.get(['natureOFOffer'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
      offerValidity: this.editForm.get(['offerValidity'])!.value,
      offerAllocation: this.editForm.get(['offerAllocation'])!.value,
      offerEndDate: this.editForm.get(['offerEndDate'])!.value
        ? moment(this.editForm.get(['offerEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      riskOfMarketShareLoss: this.editForm.get(['riskOfMarketShareLoss'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IForm>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
