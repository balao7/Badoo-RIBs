package com.badoo.ribs.tutorials.tutorial4.rib.hello_world

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial4.R
import com.badoo.ribs.tutorials.tutorial4.rib.hello_world.HelloWorldRouter.Configuration
import com.badoo.ribs.tutorials.tutorial4.rib.hello_world.HelloWorldRouter.Configuration.Content
import com.badoo.ribs.tutorials.tutorial4.rib.hello_world.mapper.ViewEventToOutput
import com.badoo.ribs.tutorials.tutorial4.util.Lexem
import com.badoo.ribs.tutorials.tutorial4.util.User
import io.reactivex.functions.Consumer
import com.badoo.ribs.core.BuildContext

class HelloWorldInteractor(
    user: User,
    config: HelloWorld.Config,
    private val output: Consumer<HelloWorld.Output>,
    buildContext: BuildContext<Nothing?>,
    router: Router<Configuration, Nothing, Content, Nothing, HelloWorldView>
) : Interactor<Configuration, Content, Nothing, HelloWorldView>(
    buildContext = buildContext,
    router = router,
    disposables = null
) {

    override fun onViewCreated(view: HelloWorldView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        view.accept(initialViewModel)
        viewLifecycle.startStop {
            bind(view to output using ViewEventToOutput)
        }
    }

    private val initialViewModel =
        HelloWorldView.ViewModel(
            titleText = Lexem.Resource(R.string.hello_world_title, user.name()),
            welcomeText = config.welcomeMessage
        )
}
