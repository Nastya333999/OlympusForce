package com.app.olympusforce.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.app.olympusforce.BaseBindingFragment
import com.app.olympusforce.Const
import com.app.olympusforce.R
import com.app.olympusforce.databinding.FragmentWVBinding
import com.app.olympusforce.view.MainViewModel
import com.google.android.material.snackbar.Snackbar

class WVFragment : BaseBindingFragment<FragmentWVBinding>(FragmentWVBinding::inflate) {

    private lateinit var wView: WebView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var vCback: ValueCallback<Array<Uri?>>
    private val args: WVFragmentArgs by navArgs()

    val data = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        vCback.onReceiveValue(it.toTypedArray())
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        requireActivity().window.statusBarColor =
            resources.getColor(R.color.black, requireActivity().theme)

        wView = binding.immediateWebView
        CookieManager.getInstance().setAcceptThirdPartyCookies(wView, true)
        CookieManager.getInstance().setAcceptCookie(true)

        wView.loadUrl(args.url)
        wView.settings.loadWithOverviewMode = false
        wView.settings.userAgentString =
            wView.settings.userAgentString.replace("wv", "")

        wView.settings.javaScriptEnabled = true
        wView.settings.domStorageEnabled = true

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (wView.canGoBack()) {
                        wView.goBack()
                    } else {
//                        isEnabled = false
                    }
                }
            })

        wView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)
                Snackbar.make(view, error.description, Snackbar.LENGTH_LONG).show()
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                CookieManager.getInstance().flush()

                if ((Const.PR + Const.BUU) == url) {
                    val intent = Intent(context, GActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    if (url.isNotEmpty() && !url.contains(BU)) {
                        viewModel.saveUrl(url)
                        Log.e("mistake here", "$url")
                    }
                }
            }
        }

        wView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri?>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                vCback = filePathCallback
                data.launch(IMAGE_MIME_TYPE)
                return true
            }

            @SuppressLint("SetJavaScriptEnabled")
            override fun onCreateWindow(
                view: WebView?, isDialog: Boolean,
                isUserGesture: Boolean, resultMsg: Message
            ): Boolean {
                val nWv = WebView(requireContext())
                nWv.settings.javaScriptEnabled = true
                nWv.webChromeClient = this
                nWv.settings.javaScriptCanOpenWindowsAutomatically = true
                nWv.settings.domStorageEnabled = true
                nWv.settings.setSupportMultipleWindows(true)
                val transport = resultMsg.obj as WebView.WebViewTransport
                transport.webView = nWv
                resultMsg.sendToTarget()
                return true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    companion object {
        const val BU = "clashofgold.online/"
        private const val IMAGE_MIME_TYPE = "image/*"
    }


}