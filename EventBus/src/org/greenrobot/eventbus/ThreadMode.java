/*
 * Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.greenrobot.eventbus;

/**
 * Each event handler method has a thread mode, which determines in which thread the method is to be called by EventBus.
 * EventBus takes care of threading independently from the posting thread.
 *
 * @author Markus
 * @see EventBus#register(Object)
 */
public enum ThreadMode {
    /**
     * 官方：订阅者将在发布事件的同一线程中被调用。这是默认设置。事件传递意味着最少的开销，因为它完全避免了线程切换。
     * 因此，对于已知在不需要主线程的情况下完成的简单任务，这是推荐的模式。使用此模式的事件处理程序必须快速返回以避免阻塞可能是主线程的发布线程。
     * <p>
     * 表示在执行poast事件操作时，线程直接调用订阅者的事件方法，不论事件是否在主线程，主线程中不能做耗时操作
     */

    POSTING,

    /**
     * 官方：订阅者会在 Android 的主线程（有时称为 UI 线程）中被调用。
     * 如果发布线程是主线程，则将直接调用事件处理程序方法。使用此模式的事件处理程序必须快速返回以避免阻塞主线程。
     * <p>
     * 表示在主线程中执行方法
     */
    MAIN,

    /**
     * 官方：订阅者将在后台线程中被调用。如果发帖线程不是主线程，则将直接在发帖线程中调用事件处理程序方法。
     * 如果发布线程是主线程，则 EventBus 使用单个后台线程，它将按顺序传递其所有事件。
     * 使用此模式的事件处理程序应尝试快速返回以避免阻塞后台线程。
     * <p>
     * 使用BackgroundPoster去调度
     * 表示在后台线程中执行方法，如果发布线程不在主线程时调用。
     * 由于它是唯一的，所以事件发送的poast超过一个时，会放在后台线程中去依次处理
     */
    BACKGROUND,

    /**
     * 官方：事件处理程序方法在单独的线程中调用。这始终独立于发布线程和主线程。使用此模式发布事件从不等待事件处理程序方法。
     * 如果事件处理程序方法的执行可能需要一些时间，则应使用此模式，例如用于网络访问。
     * 避免同时触发大量长时间运行的异步处理程序方法以限制并发线程数。 EventBus 使用线程池来有效地重用来自已完成的异步事件处理程序通知的线程。
     *
     * 使用AsyncPoster调用
     * 表示不论发布线程是否是主线程，都会使用一个空线程来进行处理，
     * 与BACKGROUP区别是所有的线程都相互独立，不会出现线程卡顿
     * 因为AsyncPoster每次只会从线程队列中获取一个pendingPost
     * 而BackgroundPoster会把所有的pendingPost取出来
     */
    ASYNC
}