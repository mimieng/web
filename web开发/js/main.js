// 等待DOM加载完成
document.addEventListener('DOMContentLoaded', function() {
    // 添加页面加载动画类
    document.body.classList.add('page-loaded');
    
    // 初始化轮播图
    initSlider();
    
    // 初始化FAQ折叠面板
    initFAQ();
    
    // 初始化表单验证
    initFormValidation();
    
    // 初始化滚动动画
    initScrollAnimations();
    
    // 初始化视频播放
    initVideoPlayer();
    
    // 初始化移动端导航
    initMobileNav();
});

// 轮播图功能
function initSlider() {
    const slider = document.querySelector('.slider');
    if (!slider) return;
    
    const slides = slider.querySelectorAll('.slide');
    const dots = document.querySelectorAll('.dot');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');
    
    let currentSlide = 0;
    let slideInterval;
    
    // 显示指定幻灯片
    function showSlide(index) {
        // 移除所有幻灯片的active类
        slides.forEach(slide => {
            slide.classList.remove('active');
        });
        
        // 移除所有点的active类
        dots.forEach(dot => {
            dot.classList.remove('active');
        });
        
        // 添加当前幻灯片和点的active类
        slides[index].classList.add('active');
        if (dots.length > 0) {
            dots[index].classList.add('active');
        }
        
        // 更新当前幻灯片索引
        currentSlide = index;
    }
    
    // 显示下一张幻灯片
    function nextSlide() {
        let next = currentSlide + 1;
        if (next >= slides.length) {
            next = 0;
        }
        showSlide(next);
    }
    
    // 显示上一张幻灯片
    function prevSlide() {
        let prev = currentSlide - 1;
        if (prev < 0) {
            prev = slides.length - 1;
        }
        showSlide(prev);
    }
    
    // 开始自动播放
    function startSlideInterval() {
        slideInterval = setInterval(nextSlide, 5000);
    }
    
    // 停止自动播放
    function stopSlideInterval() {
        clearInterval(slideInterval);
    }
    
    // 初始化显示第一张幻灯片
    showSlide(0);
    
    // 开始自动播放
    startSlideInterval();
    
    // 点击下一张按钮
    if (nextBtn) {
        nextBtn.addEventListener('click', function() {
            stopSlideInterval();
            nextSlide();
            startSlideInterval();
        });
    }
    
    // 点击上一张按钮
    if (prevBtn) {
        prevBtn.addEventListener('click', function() {
            stopSlideInterval();
            prevSlide();
            startSlideInterval();
        });
    }
    
    // 点击导航点
    dots.forEach((dot, index) => {
        dot.addEventListener('click', function() {
            stopSlideInterval();
            showSlide(index);
            startSlideInterval();
        });
    });
    
    // 鼠标悬停时停止自动播放
    slider.addEventListener('mouseenter', stopSlideInterval);
    
    // 鼠标离开时恢复自动播放
    slider.addEventListener('mouseleave', startSlideInterval);
}

// FAQ折叠面板功能
function initFAQ() {
    const faqItems = document.querySelectorAll('.faq-item');
    
    faqItems.forEach(item => {
        const question = item.querySelector('.faq-question');
        
        question.addEventListener('click', function() {
            // 切换当前项的active类
            item.classList.toggle('active');
            
            // 更新切换图标
            const toggleIcon = question.querySelector('.toggle-icon');
            if (toggleIcon) {
                toggleIcon.textContent = item.classList.contains('active') ? '−' : '+';
            }
        });
    });
}

// 表单验证功能
function initFormValidation() {
    const contactForm = document.getElementById('contactForm');
    if (!contactForm) return;
    
    contactForm.addEventListener('submit', function(event) {
        let isValid = true;
        
        // 获取必填字段
        const requiredFields = contactForm.querySelectorAll('[required]');
        
        // 验证每个必填字段
        requiredFields.forEach(field => {
            if (!field.value.trim()) {
                isValid = false;
                field.classList.add('error');
                
                // 如果字段旁边没有错误消息，则添加一个
                let errorMsg = field.nextElementSibling;
                if (!errorMsg || !errorMsg.classList.contains('error-message')) {
                    errorMsg = document.createElement('span');
                    errorMsg.classList.add('error-message');
                    errorMsg.style.color = 'red';
                    errorMsg.style.fontSize = '0.8rem';
                    errorMsg.textContent = '此字段为必填项';
                    field.parentNode.insertBefore(errorMsg, field.nextSibling);
                }
            } else {
                field.classList.remove('error');
                
                // 移除错误消息
                const errorMsg = field.nextElementSibling;
                if (errorMsg && errorMsg.classList.contains('error-message')) {
                    errorMsg.remove();
                }
                
                // 验证邮箱格式
                if (field.type === 'email') {
                    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                    if (!emailPattern.test(field.value)) {
                        isValid = false;
                        field.classList.add('error');
                        
                        const emailError = document.createElement('span');
                        emailError.classList.add('error-message');
                        emailError.style.color = 'red';
                        emailError.style.fontSize = '0.8rem';
                        emailError.textContent = '请输入有效的邮箱地址';
                        field.parentNode.insertBefore(emailError, field.nextSibling);
                    }
                }
            }
        });
        
        // 如果表单无效，阻止提交
        if (!isValid) {
            event.preventDefault();
            
            // 显示表单顶部的错误消息
            let formError = contactForm.querySelector('.form-error');
            if (!formError) {
                formError = document.createElement('div');
                formError.classList.add('form-error');
                formError.style.color = 'red';
                formError.style.marginBottom = '15px';
                formError.style.padding = '10px';
                formError.style.backgroundColor = '#ffebee';
                formError.style.borderRadius = '4px';
                formError.textContent = '请填写所有必填字段';
                contactForm.insertBefore(formError, contactForm.firstChild);
            }
            
            // 滚动到表单顶部
            formError.scrollIntoView({ behavior: 'smooth', block: 'start' });
        } else {
            // 移除表单顶部的错误消息
            const formError = contactForm.querySelector('.form-error');
            if (formError) {
                formError.remove();
            }
            
            // 在这里可以添加AJAX表单提交代码
            // 为了演示，我们只是阻止默认提交并显示成功消息
            event.preventDefault();
            
            // 显示成功消息
            const successMsg = document.createElement('div');
            successMsg.classList.add('form-success');
            successMsg.style.color = 'green';
            successMsg.style.marginBottom = '15px';
            successMsg.style.padding = '10px';
            successMsg.style.backgroundColor = '#e8f5e9';
            successMsg.style.borderRadius = '4px';
            successMsg.textContent = '表单提交成功！我们将尽快与您联系。';
            contactForm.insertBefore(successMsg, contactForm.firstChild);
            
            // 重置表单
            contactForm.reset();
            
            // 5秒后移除成功消息
            setTimeout(() => {
                successMsg.remove();
            }, 5000);
        }
    });
    
    // 输入时移除错误状态
    const formFields = contactForm.querySelectorAll('input, textarea, select');
    formFields.forEach(field => {
        field.addEventListener('input', function() {
            field.classList.remove('error');
            
            // 移除错误消息
            const errorMsg = field.nextElementSibling;
            if (errorMsg && errorMsg.classList.contains('error-message')) {
                errorMsg.remove();
            }
        });
    });
}

// 滚动动画功能
function initScrollAnimations() {
    // 获取所有需要动画的元素
    const animatedElements = document.querySelectorAll('.fade-in, .slide-up, .slide-right, .slide-left, .zoom-in');
    
    // 检查元素是否在视口中
    function isElementInViewport(el) {
        const rect = el.getBoundingClientRect();
        return (
            rect.top <= (window.innerHeight || document.documentElement.clientHeight) * 0.8 &&
            rect.bottom >= 0
        );
    }
    
    // 处理滚动事件
    function handleScroll() {
        animatedElements.forEach(element => {
            if (isElementInViewport(element) && !element.classList.contains('animated')) {
                element.classList.add('animated');
                
                // 根据元素的动画类型添加相应的动画
                if (element.classList.contains('fade-in')) {
                    element.style.opacity = '0';
                    setTimeout(() => {
                        element.style.transition = 'opacity 1s ease';
                        element.style.opacity = '1';
                    }, 100);
                } else if (element.classList.contains('slide-up')) {
                    element.style.transform = 'translateY(50px)';
                    element.style.opacity = '0';
                    setTimeout(() => {
                        element.style.transition = 'transform 0.8s ease, opacity 0.8s ease';
                        element.style.transform = 'translateY(0)';
                        element.style.opacity = '1';
                    }, 100);
                } else if (element.classList.contains('slide-right')) {
                    element.style.transform = 'translateX(-50px)';
                    element.style.opacity = '0';
                    setTimeout(() => {
                        element.style.transition = 'transform 0.8s ease, opacity 0.8s ease';
                        element.style.transform = 'translateX(0)';
                        element.style.opacity = '1';
                    }, 100);
                } else if (element.classList.contains('slide-left')) {
                    element.style.transform = 'translateX(50px)';
                    element.style.opacity = '0';
                    setTimeout(() => {
                        element.style.transition = 'transform 0.8s ease, opacity 0.8s ease';
                        element.style.transform = 'translateX(0)';
                        element.style.opacity = '1';
                    }, 100);
                } else if (element.classList.contains('zoom-in')) {
                    element.style.transform = 'scale(0.8)';
                    element.style.opacity = '0';
                    setTimeout(() => {
                        element.style.transition = 'transform 0.8s ease, opacity 0.8s ease';
                        element.style.transform = 'scale(1)';
                        element.style.opacity = '1';
                    }, 100);
                }
            }
        });
    }
    
    // 初始检查
    handleScroll();
    
    // 添加滚动事件监听器
    window.addEventListener('scroll', handleScroll);
}

// 视频播放功能
function initVideoPlayer() {
    const videoPlaceholders = document.querySelectorAll('.video-placeholder');
    
    videoPlaceholders.forEach(placeholder => {
        const playButton = placeholder.nextElementSibling;
        if (!playButton || !playButton.classList.contains('play-button')) return;
        
        playButton.addEventListener('click', function() {
            // 创建视频元素
            const video = document.createElement('video');
            video.controls = true;
            video.autoplay = true;
            video.style.width = '100%';
            video.style.height = '100%';
            video.style.objectFit = 'cover';
            
            // 设置视频源（使用相对路径而非绝对路径）
            const source = document.createElement('source');
            source.src = './video/1.mp4'; // 使用相对路径
            source.type = 'video/mp4';
            
            // 添加错误处理
            video.addEventListener('error', function(e) {
                console.error('视频加载失败:', e);
                alert('视频加载失败，请检查视频文件路径是否正确');
            });
            
            video.appendChild(source);
            
            // 替换占位符图像
            const parent = placeholder.parentNode;
            parent.replaceChild(video, placeholder);
            
            // 移除播放按钮
            playButton.remove();
        });
    });
}

// 移动端导航功能
function initMobileNav() {
    // 检查是否是移动设备
    const isMobile = window.innerWidth <= 768;
    
    if (isMobile) {
        // 创建汉堡菜单按钮
        const hamburger = document.createElement('div');
        hamburger.classList.add('hamburger-menu');
        hamburger.innerHTML = '<span></span><span></span><span></span>';
        
        // 获取导航元素
        const nav = document.querySelector('nav');
        const header = document.querySelector('header .container');
        
        // 将汉堡菜单添加到头部
        if (header && nav) {
            header.insertBefore(hamburger, nav);
            
            // 添加移动端导航样式
            nav.style.display = 'none';
            nav.style.position = 'absolute';
            nav.style.top = '100%';
            nav.style.left = '0';
            nav.style.width = '100%';
            nav.style.backgroundColor = '#fff';
            nav.style.boxShadow = '0 5px 10px rgba(0, 0, 0, 0.1)';
            nav.style.zIndex = '999';
            
            // 点击汉堡菜单切换导航显示
            hamburger.addEventListener('click', function() {
                hamburger.classList.toggle('active');
                nav.style.display = nav.style.display === 'none' ? 'block' : 'none';
            });
            
            // 为子菜单添加点击事件
            const hasSubMenus = document.querySelectorAll('.main-nav > li');
            hasSubMenus.forEach(item => {
                if (item.querySelector('.sub-nav')) {
                    const link = item.querySelector('a');
                    link.addEventListener('click', function(e) {
                        if (window.innerWidth <= 768) {
                            e.preventDefault();
                            const subNav = item.querySelector('.sub-nav');
                            subNav.style.display = subNav.style.display === 'block' ? 'none' : 'block';
                        }
                    });
                }
            });
        }
    }
    
    // 监听窗口大小变化
    window.addEventListener('resize', function() {
        const nav = document.querySelector('nav');
        if (window.innerWidth > 768) {
            // 恢复桌面端导航样式
            if (nav) {
                nav.style.display = 'block';
                nav.style.position = 'static';
                nav.style.width = 'auto';
                nav.style.boxShadow = 'none';
            }
            
            // 移除汉堡菜单
            const hamburger = document.querySelector('.hamburger-menu');
            if (hamburger) {
                hamburger.remove();
            }
        } else {
            // 如果没有汉堡菜单，重新初始化移动端导航
            const hamburger = document.querySelector('.hamburger-menu');
            if (!hamburger) {
                initMobileNav();
            }
        }
    });
}

// 平滑滚动到锚点
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        const targetId = this.getAttribute('href');
        if (targetId === '#') return;
        
        const targetElement = document.querySelector(targetId);
        if (targetElement) {
            e.preventDefault();
            window.scrollTo({
                top: targetElement.offsetTop - 80, // 减去头部高度
                behavior: 'smooth'
            });
        }
    });
});

// 返回顶部按钮
window.addEventListener('scroll', function() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    
    // 检查是否已存在返回顶部按钮
    let backToTop = document.querySelector('.back-to-top');
    
    if (scrollTop > 300) {
        // 如果滚动超过300px且按钮不存在，则创建按钮
        if (!backToTop) {
            backToTop = document.createElement('div');
            backToTop.classList.add('back-to-top');
            backToTop.innerHTML = '↑';
            backToTop.style.position = 'fixed';
            backToTop.style.bottom = '20px';
            backToTop.style.right = '20px';
            backToTop.style.width = '40px';
            backToTop.style.height = '40px';
            backToTop.style.backgroundColor = 'var(--primary-color)';
            backToTop.style.color = '#fff';
            backToTop.style.borderRadius = '50%';
            backToTop.style.display = 'flex';
            backToTop.style.justifyContent = 'center';
            backToTop.style.alignItems = 'center';
            backToTop.style.cursor = 'pointer';
            backToTop.style.zIndex = '999';
            backToTop.style.boxShadow = '0 2px 5px rgba(0, 0, 0, 0.2)';
            backToTop.style.transition = 'background-color 0.3s ease';
            
            // 添加悬停效果
            backToTop.addEventListener('mouseenter', function() {
                this.style.backgroundColor = '#c1121f';
            });
            
            backToTop.addEventListener('mouseleave', function() {
                this.style.backgroundColor = 'var(--primary-color)';
            });
            
            // 添加点击事件
            backToTop.addEventListener('click', function() {
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth'
                });
            });
            
            document.body.appendChild(backToTop);
        } else {
            // 如果按钮已存在，显示它
            backToTop.style.display = 'flex';
        }
    } else if (backToTop) {
        // 如果滚动小于300px且按钮存在，隐藏它
        backToTop.style.display = 'none';
    }
});