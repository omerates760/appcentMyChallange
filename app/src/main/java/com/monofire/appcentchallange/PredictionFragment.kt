package com.monofire.appcentchallange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.monofire.appcentchallange.db.FbHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.event.snackbar
import com.monofire.appcentchallange.listener.PredictionListener
import com.monofire.appcentchallange.model.Prediction
import kotlinx.android.synthetic.main.fragment_prediction.*


class PredictionFragment : Fragment(), PredictionListener {


    private lateinit var fbHelper: FbHelper
    private lateinit var prediction:Prediction
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prediction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbHelper = FbHelper()
        fbHelper.predictionListener = this
        fbHelper.getPrediction()
        btnPrediction.setOnClickListener {
                if (editPre.text.isNotEmpty()){
                    if (editPre.text.toString().toInt() == prediction.predictionNumber) {
                        alertType(
                            SweetAlertDialog.SUCCESS_TYPE,
                            "Tebriklerr, çok şanslısın",
                            "Tahmin et kazan yarışmasında uğurlu sayıyı bildiğin için ${prediction.predictionPrice} altın kazandın !"
                        )
                        ShareDb.editUserTotal(
                            requireContext(),
                            ShareDb.getUserTotal(requireContext()) + prediction.predictionPrice
                        )
                    } else {
                        alertType(
                            SweetAlertDialog.ERROR_TYPE,
                            "Ne yazıkki Bilemedin",
                            "Üzülme! Gelecek haftaki uğurlu sayıyı sen bilebilirsin !"
                        )
                    }
                }else{
                    context?.snackbar(requireView(),"Lütfen tahmin ettiğiniz sayıyı girin.")
                }

        }

    }

    private fun alertType(type: Int, title: String, subTitle: String) {
        SweetAlertDialog(requireContext(), type)
            .setTitleText(title)
            .setContentText(subTitle)
            .setConfirmButton(
                "AnaSayfaya Dön"
            ) {

                findNavController().navigateUp()
                it.cancel()
            }.show()

    }

    override fun getNumber(prediction: Prediction) {
            this.prediction=prediction
    }

}