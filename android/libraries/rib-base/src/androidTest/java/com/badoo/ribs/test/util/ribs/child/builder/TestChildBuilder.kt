package com.badoo.ribs.test.util.ribs.child.builder

import com.badoo.ribs.core.BuildContext
import android.view.ViewGroup
import com.badoo.ribs.core.Builder
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.test.util.ribs.TestNode
import com.badoo.ribs.test.util.ribs.child.TestChildInteractor
import com.badoo.ribs.test.util.ribs.child.TestChildRouter
import com.badoo.ribs.test.util.ribs.child.TestChildView
import com.badoo.ribs.test.util.ribs.child.TestChildViewImpl
import com.badoo.ribs.test.util.ribs.root.TestRoot

class TestChildBuilder : Builder<Nothing?, Nothing?, TestNode<TestChildView>>() {

    override val dependency: Nothing? = null

    override fun build(buildContext: BuildContext<Nothing?>): TestNode<TestChildView> {
        val router = TestChildRouter(resolve(object: TestRoot { }, params))
        val buildContext = buildContext

        return TestNode(
            buildContext = buildContext,
            viewFactory = object : ViewFactory<Nothing?, TestChildView> {
                override fun invoke(deps: Nothing?): (ViewGroup) -> TestChildView = {
                    TestChildViewImpl(it.context)
                }
            },
            router = router,
            interactor = TestChildInteractor(buildContext, router)
        )
    }
}
