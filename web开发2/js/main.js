// 主要JavaScript功能
document.addEventListener('DOMContentLoaded', function() {
    // 平滑滚动
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth'
                });
            }
        });
    });
    
    // 参观者感言轮播
    const testimonialItems = document.querySelectorAll('.testimonial-item');
    const testimonialDots = document.querySelectorAll('.testimonial-dots .dot');
    
    if (testimonialItems.length > 0 && testimonialDots.length > 0) {
        let currentTestimonial = 0;
        
        // 显示指定感言
        function showTestimonial(index) {
            testimonialItems.forEach(item => {
                item.style.display = 'none';
            });
            
            testimonialDots.forEach(dot => {
                dot.classList.remove('active');
            });
            
            testimonialItems[index].style.display = 'block';
            testimonialDots[index].classList.add('active');
            
            currentTestimonial = index;
        }
        
        // 初始化显示第一个
        showTestimonial(0);
        
        // 点击切换
        testimonialDots.forEach((dot, index) => {
            dot.addEventListener('click', () => {
                showTestimonial(index);
            });
        });
        
        // 自动切换
        setInterval(() => {
            let next = currentTestimonial + 1;
            if (next >= testimonialItems.length) {
                next = 0;
            }
            showTestimonial(next);
        }, 6000);
    }
    
    // 动画效果
    const animateElements = document.querySelectorAll('.animate-on-scroll');
    
    function checkScroll() {
        animateElements.forEach(element => {
            const elementTop = element.getBoundingClientRect().top;
            const windowHeight = window.innerHeight;
            
            if (elementTop < windowHeight * 0.8) {
                element.classList.add('animate');
            }
        });
    }
    
    // 初始检查
    checkScroll();
    
    // 滚动时检查
    window.addEventListener('scroll', checkScroll);
});